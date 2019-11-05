public class Formitemicon {











































































































































































































































































	protected Set<FormItemClickHandler> getFormItemClickHandlers() {
		return Sets.newHashSet(formItemClickHandlers);
	}


	public HandlerRegistration addFormItemClickHandler(final FormItemClickHandler handler) {
		formItemClickHandlers.add(handler);
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				formItemClickHandlers.remove(handler);
			}
		};
	}


	public void paintContent(PaintTarget target) throws PaintException {
		if (!formItemClickHandlers.isEmpty()) {
			target.addAttribute("*hasFormItemClickHandlers", true);
		}

		super.paintContent(target);
	}


	public void changeVariables(Object source, Map<String, Object> variables) {
		final Map<String, Object> formItemIconClickEventVariables = filterComplexVariable(variables, "formItemIconClickEvent");

		if (!formItemIconClickEventVariables.isEmpty()) {
			final DynamicForm form = (DynamicForm) formItemIconClickEventVariables.get("form");
			final FormItem item = (FormItem) formItemIconClickEventVariables.get("item");
			final FormItemIcon icon = (FormItemIcon) formItemIconClickEventVariables.get("icon");
			final FormItemIconClickEvent event = new FormItemIconClickEvent(form, item, icon);
			for (FormItemClickHandler handler : formItemClickHandlers) {
				handler.onFormItemClick(event);
			}
		}

		super.changeVariables(source, variables);
	}












}
