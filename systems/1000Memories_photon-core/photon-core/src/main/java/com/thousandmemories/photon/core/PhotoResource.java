public class Photoresource {







































    public Response getPhoto(@PathParam("name") String name,
                             @MatrixParam("w") Integer width,
                             @MatrixParam("r") RotationParam rotateAngle,
                             @MatrixParam("c") RectangleParam crop) throws Exception {
        InputStream resultStream;

        InputStream imageStream;
        try {
            imageStream = new BufferedInputStream(photoProvider.getPhotoInputStream(name));
        } catch (FileNotFoundException fnfe) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        String mimeType = URLConnection.guessContentTypeFromStream(imageStream);
        if (mimeType == null) {
            throw new WebApplicationException(501); // Not implemented
        }

        if (width != null || rotateAngle != null || crop != null) {
            BufferedImage image;
            TimerContext readContext = readTimer.time();
            try {
                image = ImageIO.read(imageStream);
            } finally {
                imageStream.close();
                readContext.stop();
            }

            if (crop != null) {
                image = com.thousandmemories.photon.core.Processor.crop(image, crop.get());
            }

            if (rotateAngle != null) {
                image = com.thousandmemories.photon.core.Processor.rotate(image, rotateAngle.get());
            }

            if (width != null) {
                image = com.thousandmemories.photon.core.Processor.fitToWidth(image, width);
            }

            Iterator<ImageWriter> i = ImageIO.getImageWritersByMIMEType(mimeType);
            if (!i.hasNext()) {
                mimeType = "image/jpeg";
                i = ImageIO.getImageWritersByMIMEType(mimeType);
            }

            ImageWriter writer = i.next();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writer.setOutput(new MemoryCacheImageOutputStream(os));
            writer.write(image);
            image.flush();
            image = null;
            resultStream = new ByteArrayInputStream(os.toByteArray());
        } else {
            resultStream = imageStream;
        }

        return Response.
                ok(resultStream, mimeType).
                build();

    }
}
