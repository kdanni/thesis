package hu.bme.mit.v37zen.prepayment.util.xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
        objectName="bean:name=NamespaceHandler")
public class NamespaceHandler  {
	
	public final static Logger logger = LoggerFactory.getLogger(NamespaceHandler.class);
	
	private Map<String, String> namespaces = new HashMap<String, String>();
	
	private String namespaceDefinitionFilePath;
	
	private ResourceLoaderBean resourceLoaderBean;
	
	
	public NamespaceHandler(ResourceLoaderBean resourceLoaderBean, String namespaceDefinitionFilePath) {
		super();
		this.resourceLoaderBean = resourceLoaderBean;
		this.namespaceDefinitionFilePath = namespaceDefinitionFilePath;
		this.readNamespaceDefinitionFile();
	}


	@ManagedOperation
	public Map<String, String> getNamespaces() {
		return namespaces;
	}

	@ManagedOperation
	public void addNamespace(String prefix, String URI){
		
		namespaces.put(prefix, URI);
	}
	
	@ManagedOperation
	public void clearNamespaces(){
		
		this.namespaces.clear();
	}

	@ManagedAttribute
	public String getNamespaceDefinitionFilePath() {
		return namespaceDefinitionFilePath;
	}

	@ManagedAttribute
	public void setNamespaceDefinitionFilePath(
			String namespaceDefinitionFilePath) {
		this.namespaceDefinitionFilePath = namespaceDefinitionFilePath;
	}
	
	
	

	@ManagedOperation
	public void readNamespaceDefinitionFile(){
		Resource resource = resourceLoaderBean.getResourceLoader().getResource(namespaceDefinitionFilePath);
		try {
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			logger.info("Namespace definition file has been found.");

			String line;
			StringBuffer buff = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buff.append(line);
				buff.append('\n');
				
				line = line.trim();
				if (line.charAt(0) != '#'){
					String[] splitedLine = line.split("\\s+");
					if (splitedLine.length > 1){ 
						this.addNamespace(splitedLine[0],splitedLine[1]);
					}
				}
			}
			br.close();
			logger.debug("[Namespace definition file:]\n" + buff.toString());

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}


	public ResourceLoaderBean getResourceLoaderBean() {
		return resourceLoaderBean;
	}


	public void setResourceLoaderBean(ResourceLoaderBean resourceLoaderBean) {
		this.resourceLoaderBean = resourceLoaderBean;
	}

}
