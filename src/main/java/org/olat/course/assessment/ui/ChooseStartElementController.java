/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.course.assessment.ui;

import java.util.Collections;

import org.olat.core.gui.UserRequest;
import org.olat.core.gui.components.form.flexible.FormItemContainer;
import org.olat.core.gui.components.form.flexible.impl.FormBasicController;
import org.olat.core.gui.components.tree.MenuTreeItem;
import org.olat.core.gui.control.Controller;
import org.olat.core.gui.control.Event;
import org.olat.core.gui.control.WindowControl;
import org.olat.core.id.OLATResourceable;
import org.olat.core.util.resource.OresHelper;
import org.olat.course.CourseFactory;
import org.olat.course.ICourse;
import org.olat.course.nodes.CourseNode;
import org.olat.course.tree.CourseEditorTreeModel;

/**
 * 
 * Initial date: 19.12.2014<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class ChooseStartElementController extends FormBasicController {

	private MenuTreeItem selectTree;
	private CourseEditorTreeModel treeModel;

	private final OLATResourceable ores;
	private final String preSelectedKey;

	public ChooseStartElementController(UserRequest ureq, WindowControl wControl, String selectedKey, OLATResourceable ores) {
		super(ureq, wControl, "course_element");
		this.ores = OresHelper.clone(ores);
		preSelectedKey = selectedKey;
		initForm(ureq);
	}
	
	@Override
	protected void doDispose() {
		// nothing to dispose
	}

	@Override
	protected void initForm(FormItemContainer formLayout, Controller listener, UserRequest ureq) {
		ICourse course = CourseFactory.loadCourse(ores);
		treeModel = course.getEditorTreeModel();
		selectTree = uifactory.addTreeMultiselect("elements", null, formLayout, treeModel, this);
		selectTree.setSelectedKeys(Collections.singletonList(preSelectedKey));
		
		uifactory.addFormCancelButton("cancel", formLayout, ureq, getWindowControl());
		uifactory.addFormSubmitButton("ok", formLayout);
	}
	
	public String getSelectedKey() {
		return selectTree.getSelectedNodeId();
	}
	
	public String getSelectedName() {
		String selectedKey = getSelectedKey();
		String name = null;

		CourseNode node = treeModel.getCourseNode(selectedKey);
		if(node == null) {
			//not published??
		} else {
			name = node.getShortTitle();
		}
		return name;
	}

	@Override
	protected void formOK(UserRequest ureq) {
		fireEvent(ureq, Event.DONE_EVENT);
	}

	@Override
	protected void formCancelled(UserRequest ureq) {
		fireEvent(ureq, Event.CANCELLED_EVENT);
	}
}