package bpmn2.tutorial;

import org.eclipse.bpmn2.modeler.core.features.activity.task.AddTaskFeature;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.TaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectFeatureContainer;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;

public class ImixsDataObjectFeatureContainer extends TaskFeatureContainer {


	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new ImixsAddNewTaskFeature(fp);
	}
	
	public static class ImixsAddNewTaskFeature extends AddTaskFeature {

		public ImixsAddNewTaskFeature(IFeatureProvider fp) {
			super(fp);
		}

		
		
	}

}