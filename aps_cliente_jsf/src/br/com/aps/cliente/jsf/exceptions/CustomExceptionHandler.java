package br.com.aps.cliente.jsf.exceptions;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	final NavigationHandler navigationHandler = facesContext.getApplication()
			.getNavigationHandler();

	private ExceptionHandler wrapped;

	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents()
				.iterator();
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();
			Throwable exception = context.getException();
			try {
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Atenção", exception.getMessage()));
			} finally {
				iterator.remove();
			}
		}
		getWrapped().handle();
	}
}
