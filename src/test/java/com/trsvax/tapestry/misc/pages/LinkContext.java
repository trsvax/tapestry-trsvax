package com.trsvax.tapestry.misc.pages;

import com.trsvax.tapestry.misc.annotations.LinkWithContext;

public class LinkContext {
	
	@LinkWithContext(page=Page1.class)
	Object onContextLink() {
		return "context";
	}

}
