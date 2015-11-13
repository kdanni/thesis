package hu.bme.inf.v37zen.re.debug.main;

import hu.bme.inf.v37zen.re.debug.model.DebugEntity;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Debug {

	public static void main(String[] args) {
		DebugEntity d = new DebugEntity();
		System.out.println("Value: " + d.getValue());

		KieContainer kContainer;
		KieSession kSession;

		KieServices ks = KieServices.Factory.get();
		kContainer = ks.getKieClasspathContainer();
		kSession = kContainer.newKieSession("ksession-rules");

		kSession.insert(d);

		kSession.fireAllRules();
		kSession.destroy();

		System.out.println("Value: " + d.getValue());

	}

}
