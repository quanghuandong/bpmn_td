package bpmn2.tutorial;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.modeler.core.features.CustomShapeFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.IShapeFeatureContainer;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.runtime.IObjectDecorator;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.TaskFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.activity.task.TaskFeatureContainer.CreateTaskFeature;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectFeatureContainer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

import bpmn2.tutorial.ImixsDataObjectFeatureContainer.ImixsAddNewTaskFeature;
import bpmn2.tutorial.ImixsFeatureContainerTask.ImixsCreateCustomTaskFeature;
import bpmn2.tutorial.ImixsFeatureContainerTask.ImixsmAddCustomTaskFeature;


/**
 * Imixs ProcessEntity task container
 * 
 * @author rsoika
 *
 */
public class ImixsFeatureContainerDataObject extends CustomShapeFeatureContainer {
 
	// these values must match what's in the plugin.xml
	private final static String TYPE_VALUE = "MyTask";
	public final static String TECHNICALDEBT_DATAOBJECT_ID = "bpmn2.tutorial.TechnicalDebtDataObject";
	private static final IColorConstant TECHNICALDEBT_DATAOBJECT_BACKGROUND = new ColorConstant(
			144, 76, 24);
			//255, 216, 0);
  
	private static final IColorConstant TECHNICALDEBT_DATAOBJECT_FOREGROUND = new ColorConstant(
			0, 0, 0);
	
	public ImixsFeatureContainerDataObject() {		
	}
	
	
	@Override
	public boolean canApplyTo(Object o) {
		boolean b1 =  o instanceof Task;
		boolean b2 = o.getClass().isAssignableFrom(Task.class);
		return b1 || b2;
	}

	
	@Override
	protected IShapeFeatureContainer createFeatureContainer(IFeatureProvider fp) {
		return new TaskFeatureContainer() {
			@Override
			public ICreateFeature getCreateFeature(IFeatureProvider fp) {
				return new ImixsCreateCustomDataObjectFeature(fp);
			}
			
			@Override
			public IAddFeature getAddFeature(IFeatureProvider fp) {
				return new ImixsmAddCustomDataObjectFeature(fp);
			}
		};
	}
	
	
	protected class ImixsCreateCustomDataObjectFeature extends CreateTaskFeature {

		public ImixsCreateCustomDataObjectFeature(IFeatureProvider fp) {
			super(fp);
		}

		
	}
	
	
	protected class ImixsmAddCustomDataObjectFeature extends ImixsAddNewTaskFeature {

		public ImixsmAddCustomDataObjectFeature(IFeatureProvider fp) {
			super(fp);
		}
		
		@Override
		protected void decorateShape(IAddContext context, ContainerShape containerShape, Task businessObject) {
			
			super.decorateShape(context, containerShape, businessObject);
			setFillColor(containerShape);
			
		
			
		}
	}
	
	/**
	 * 
	 * This method inspects the object to determine what its custom task ID
	 * should be. In this case, we check the namespace of the "type" attribute.
	 * If the namespace matches the imixs targetNamespace, return the
	 * PROCESSENTITY_TASK_ID string.
	 */
	@Override
	public String getId(EObject object) {
//		if (ImixsBPMNPlugin.isImixsTask(object)) {
//			return TECHNICALDEBT_DATAOBJECT_ID;
//		}
		
		EStructuralFeature f = ModelDecorator.getAnyAttribute(object, "type");
		if (f!=null) {
			Object id = object.eGet(f);
			if (TYPE_VALUE.equals(id))
				return TECHNICALDEBT_DATAOBJECT_ID;
		}
			
		return null;
		
		
	}

	
 

	/**
	 * Common method used to set the fill color for Imixs CustomTask
	 * figure. This method is called by both the CreateFeature and the
	 * UpdateFeature.
	 * 
	 * @param containerShape
	 *            - the ContainerShape that corresponds to the Task.
	 */
	private void setFillColor(ContainerShape containerShape) {
		Task ta = BusinessObjectUtil.getFirstElementOfType(
				containerShape, Task.class);
		if (ta != null) {
			// ExtendedPropertiesAdapter adapter =
			// ExtendedPropertiesAdapter.adapt(ta);
			// Boolean attributeValue =
			// (Boolean)adapter.getFeatureDescriptor("evaluate").getValue();
			Shape shape = containerShape.getChildren().get(0);
			//ShapeStyle ss = new ShapeStyle();

			//ss.setDefaultColors(PROCESSENTITY_BACKGROUND);
			//ss.setShapeForeground(PROCESSENTITY_FOREGROUND);
			shape.getGraphicsAlgorithm().setLineStyle(LineStyle.DASH);
			shape.getGraphicsAlgorithm().setLineWidth(2);
						
			//StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), ta, ss);
			// Graphiti.getPeService().setPropertyValue(containerShape,
			// "evaluate.property", propertyValue);
		}
	}

}
