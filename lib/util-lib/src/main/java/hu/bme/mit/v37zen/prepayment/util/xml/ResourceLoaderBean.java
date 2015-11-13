package hu.bme.mit.v37zen.prepayment.util.xml;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderBean implements ResourceLoaderAware {
	
	private ResourceLoader resourceLoader;

	
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}
	
	public ResourceLoader getResourceLoader(){
		return this.resourceLoader;
	}
}
