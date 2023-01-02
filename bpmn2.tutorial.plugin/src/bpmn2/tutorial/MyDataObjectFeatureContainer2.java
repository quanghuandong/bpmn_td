/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013 Red Hat, Inc.
 * All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * 	Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package bpmn2.tutorial;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.modeler.core.features.AbstractUpdateBaseElementFeature;
import org.eclipse.bpmn2.modeler.core.features.CustomShapeFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.preferences.ShapeStyle;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.features.data.DataObjectFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.IntermediateCatchEventFeatureContainer;
import org.eclipse.bpmn2.modeler.ui.features.event.IntermediateCatchEventFeatureContainer.AddIntermediateCatchEventFeature;
import org.eclipse.bpmn2.modeler.ui.features.event.IntermediateCatchEventFeatureContainer.CreateIntermediateCatchEventFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;


public class MyDataObjectFeatureContainer2 extends CustomShapeFeatureContainer {

	// these values must match what's in the plugin.xml
	private final static String TYPE_VALUE = "MyDataObject";
	private final static String CUSTOM_TASK_ID = "bpmn2.tutorial.MyDataObject2";
	private static final IColorConstant DATAOBJECT_BACKGROUND = new ColorConstant(255, 217, 64);
	
	public MyDataObjectFeatureContainer2() {
	}

	@Override
	public String getId(EObject object) {
		// This is where we inspect the object to determine what its custom task ID should be.
		// In this case, the "type" attribute will have a value of "MyBoundaryEvent".
		// If found, return the CUSTOM_TASK_ID string.
		//
		// Note that the object inspection can be arbitrarily complex and may include several
		// object features. This simple case just demonstrates what needs to happen here.
		EStructuralFeature f = ModelDecorator.getAnyAttribute(object, "type");
		if (f!=null) {
			Object id = object.eGet(f);
			if (TYPE_VALUE.equals(id))
				return CUSTOM_TASK_ID;
		}
			
		return null;
	}

	@Override
	public boolean canApplyTo(Object o) {
		boolean b1 =  o instanceof DataObject;
		boolean b2 = o.getClass().isAssignableFrom(DataObject.class);
		return b1 || b2;
	}
	
	@Override
	protected DataObjectFeatureContainer createFeatureContainer(IFeatureProvider fp) {
		return new DataObjectFeatureContainer() {

			/**
			 * override the Add Feature from the chosen Feature Container base
			 * class . Typically you will want to override the decorateShape()
			 * method which allows you to customize the graphical representation
			 * of this Custom Task figure.
			 */
			@Override
			public IAddFeature getAddFeature(IFeatureProvider fp) {
				return new AddDataObjectFeature(fp) {

					@Override
					protected void decorateShape(IAddContext context, ContainerShape containerShape,
							DataObject businessObject) {
						super.decorateShape(context, containerShape, businessObject);

						// add a notifyChangeAdapter to validate the ActiviytID
						//businessObject.eAdapters().add(new ImixsIdAdapter());
						//businessObject.eAdapters().add(new ImixsLayoutEventAdapter(containerShape));
					}
				};
			}

			/**
			 * This method MUST be overridden if we intend to add extension
			 * attributes to your business object (bpmn2 element) - see the
			 * BPMN2 tutorials for details:
			 * https://wiki.eclipse.org/BPMN2-Modeler/DeveloperTutorials
			 * 
			 */
			@Override
			public ICreateFeature getCreateFeature(IFeatureProvider fp) {
				return new CreateIntermediateCatchEventFeature(fp) {
				};
			}
			
			/**
			 * This method updates the background color
			 */
			@Override
			public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
				MultiUpdateFeature multiUpdate = (MultiUpdateFeature) super.getUpdateFeature(fp);
				multiUpdate.addFeature(new AbstractUpdateBaseElementFeature<Task>(fp) {

					@Override
					public boolean update(IUpdateContext context) {
						// force update of background color
						updateShapeStyle((ContainerShape) context.getPictogramElement());
						return true;
					}
				});

				return multiUpdate;
			}
			
			
			
			/**
			 * Set new background color. 
			 * <p>
			 * This can not be done by the Adapter class - see issue #75
			 * 
			 * @param containerShape
			 *            - the ContainerShape that corresponds to the Event.
			 */
			private void updateShapeStyle(ContainerShape containerShape) {
				IntermediateCatchEvent event = BusinessObjectUtil.getFirstElementOfType(containerShape,
						IntermediateCatchEvent.class);
				
				if (event != null) {
					// set background color
					ShapeStyle shapeStyle = new ShapeStyle();
					shapeStyle.setDefaultColors(DATAOBJECT_BACKGROUND);
					Shape shape = containerShape.getChildren().get(0);
					StyleUtil.applyStyle(shape.getGraphicsAlgorithm(), event, shapeStyle);
				}
			}

		};
	}

}
