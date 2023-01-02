package bpmn2.tutorial;

import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.modeler.core.features.activity.task.AddTaskFeature;
import org.eclipse.bpmn2.modeler.core.features.data.AddDataFeature;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectFeatureContainer;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;

public class MyDataObjectFeatureContainer extends DataObjectFeatureContainer {

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new MyAddDataObjectFeature(fp);
	}

	
	public static class MyAddDataObjectFeature extends AddTaskFeature {

		public MyAddDataObjectFeature(IFeatureProvider fp) {
			super(fp);
			// TODO Auto-generated constructor stub
		}
	
	}

// WILL NOT WORK!!
//	public static class MyAddDataObjectFeature extends AddDataFeature {
//
//		public MyAddDataObjectFeature(IFeatureProvider fp) {
//			super(fp);
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//		public String getName(ItemAwareElement t) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public Class getBusinessObjectType() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}
	
}