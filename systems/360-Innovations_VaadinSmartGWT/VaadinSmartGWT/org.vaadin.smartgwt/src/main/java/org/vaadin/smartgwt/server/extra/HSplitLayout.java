public class Hsplitlayout {













		public Builder setProportions(double proportion) {
			instance().setProportions(proportion);
			return me();
		}

		public Builder setLeftMember(Canvas member) {
			instance().setLeftMember(member);
			return me();
		}

		public Builder setRightMember(Canvas member) {
			instance().setRightMember(member);
			return me();
		}


		protected Builder me() {
			return this;
		}
	}

	public static Builder buildHSplitLayout() {
		return new Builder(new HSplitLayout());
	}



















	public void setProportions(double proportion) {
		left.setHeight(((int) (proportion * 100)) + "%");
	}

	public Canvas getLeftMember() {
		return left;
	}

	public void setLeftMember(Canvas member) {
		String width = left.getWidthAsString();
		
		if (member == null) {
			left = buildLabel("")
					.setWidth("50%")
					.setHeight("100%")
					.build();
		}
		else {
			left = member;
			left.setWidth(width);
		}
		setMembers(left,right);
	}

	public Canvas getRightMember() {
		return right;
	}

	public void setRightMember(Canvas member) {
		String width = right.getWidthAsString();

		if (member == null) {
			right = buildLabel("")
					.setWidth("*")
					.setHeight("100%")
					.build();
		}
		else {
			right = member;
			right.setWidth(width);
		}
		
		setMembers(left,right);
	}
}
