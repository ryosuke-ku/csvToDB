public class Didlparser {

























































































    public DIDLContent parseResource(String resource) throws Exception {
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
            return parse(IO.readLines(is));
        } finally {
            if (is != null) is.close();
        }
    }








    public DIDLContent parse(String xml) throws Exception {

        if (xml == null || xml.length() == 0) {
            throw new RuntimeException("Null or empty XML");
        }

        DIDLContent content = new DIDLContent();
        createRootHandler(content, this);

        log.fine("Parsing DIDL XML content");
        parse(new InputSource(new StringReader(xml)));
        return content;
    }



























































































































































    public String generate(DIDLContent content) throws Exception {
        return generate(content, false);
    }















    public String generate(DIDLContent content, boolean nestedItems) throws Exception {
        return documentToString(buildDOM(content, nestedItems), true);
    }



    protected String documentToString(Document document, boolean omitProlog) throws Exception {
        TransformerFactory transFactory = TransformerFactory.newInstance();

        // Indentation not supported on Android 2.2
        //transFactory.setAttribute("indent-number", 4);

        Transformer transformer = transFactory.newTransformer();

        if (omitProlog) {
            // TODO: UPNP VIOLATION: Terratec Noxon Webradio fails when DIDL content has a prolog
            // No XML prolog! This is allowed because it is UTF-8 encoded and required
            // because broken devices will stumble on SOAP messages that contain (even
            // encoded) XML prologs within a message body.
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        }

        // Again, Android 2.2 fails hard if you try this.
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter out = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(out));
        return out.toString();
    }














































































































































































































































































































    public void debugXML(String s) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("-------------------------------------------------------------------------------------");
            log.fine("\n" + s);
            log.fine("-------------------------------------------------------------------------------------");
        }
    }

















































































































































































































        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (!DIDLContent.NAMESPACE_URI.equals(uri)) return;

            if (localName.equals("container")) {

                Container container = createContainer(attributes);
                getInstance().addContainer(container);
                createContainerHandler(container, this);

            } else if (localName.equals("item")) {

                Item item = createItem(attributes);
                getInstance().addItem(item);
                createItemHandler(item, this);

            } else if (localName.equals("desc")) {

                DescMeta desc = createDescMeta(attributes);
                getInstance().addDescMetadata(desc);
                createDescMetaHandler(desc, this);

            }
        }





















        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (!DIDLContent.NAMESPACE_URI.equals(uri)) return;

            if (localName.equals("item")) {

                Item item = createItem(attributes);
                getInstance().addItem(item);
                createItemHandler(item, this);

            } else if (localName.equals("desc")) {

                DescMeta desc = createDescMeta(attributes);
                getInstance().addDescMetadata(desc);
                createDescMetaHandler(desc, this);

            } else if (localName.equals("res")) {

                Res res = createResource(attributes);
                if (res != null) {
                    getInstance().addResource(res);
                    createResHandler(res, this);
                }

            }

            // We do NOT support recursive container embedded in container! The schema allows it
            // but the spec doesn't:
            //
            // Section 2.8.3: Incremental navigation i.e. the full hierarchy is never returned
            // in one call since this is likely to flood the resources available to the control
            // point (memory, network bandwidth, etc.).
        }
















































        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            if (!DIDLContent.NAMESPACE_URI.equals(uri)) return;

            if (localName.equals("res")) {

                Res res = createResource(attributes);
                if (res != null) {
                    getInstance().addResource(res);
                    createResHandler(res, this);
                }

            } else if (localName.equals("desc")) {

                DescMeta desc = createDescMeta(attributes);
                getInstance().addDescMetadata(desc);
                createDescMetaHandler(desc, this);

            }
        }
























































        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);

            Element newEl = getInstance().getMetadata().createElementNS(uri, qName);
            for (int i = 0; i < attributes.getLength(); i++) {
                newEl.setAttributeNS(
                    attributes.getURI(i),
                    attributes.getQName(i),
                    attributes.getValue(i)
                );
            }
            current.appendChild(newEl);
            current = newEl;
        }






















}
