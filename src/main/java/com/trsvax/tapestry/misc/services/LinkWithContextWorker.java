package com.trsvax.tapestry.misc.services;

import java.util.List;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.func.Predicate;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.ComponentMethodAdvice;
import org.apache.tapestry5.services.ComponentMethodInvocation;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.TransformMethod;

import com.trsvax.tapestry.misc.annotations.LinkWithContext;


public class LinkWithContextWorker implements ComponentClassTransformWorker {
	
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;

	public void transform(ClassTransformation transformation, MutableComponentModel model) {
		List<TransformMethod> methods = transformation.matchMethods( new Predicate<TransformMethod>() {			
			@Override
			public boolean accept(TransformMethod method) {
				if ( method.getAnnotation(LinkWithContext.class) != null ) {
					return true;
				}
				return false;
			}
		});
		ComponentMethodAdvice advice = new ComponentMethodAdvice() {
			
			public void advise(ComponentMethodInvocation invocation) {
				invocation.proceed();
				Object result = invocation.getResult();
				if ( result != null && result.getClass().isArray()) {
					Object[] ret = (Object[]) result;
					Object[] context = new Object[ret.length - 1];
					System.arraycopy(ret, 1, context, 0, context.length);
					Class<?> page = (Class<?>) ret[0];
					Link link = pageRenderLinkSource.createPageRenderLinkWithContext(page, context);
					invocation.overrideResult(link);
				}
				
			}
		};
		for ( TransformMethod method : methods ) {	
			Class<?> page = method.getAnnotation(LinkWithContext.class).page();
			if ( page == null || page.equals( LinkWithContext.DEFAULT.class ) ) {
				method.addAdvice( advice);
			} else {
				method.addAdvice( new AddContextAdvice(page) );
			}
		}
	}
	
	class AddContextAdvice implements ComponentMethodAdvice {
		private Class<?> page;
		
		public AddContextAdvice(Class<?> page) {			
			this.page = page;
		}

		public void advise(ComponentMethodInvocation invocation) {
			invocation.proceed();
			Object result = invocation.getResult();
			if ( result == null ) {
				invocation.overrideResult(page);
				return;
			}
			if ( result.getClass().isArray()) {
				Object[] ret = (Object[]) result;
				Link link = pageRenderLinkSource.createPageRenderLinkWithContext(page, ret);
				invocation.overrideResult(link);
			} else {
				Link link = pageRenderLinkSource.createPageRenderLinkWithContext(page, result);
				invocation.overrideResult(link);
			}
		}
		
	}

}
