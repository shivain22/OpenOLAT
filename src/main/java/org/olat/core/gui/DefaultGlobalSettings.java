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
package org.olat.core.gui;

import org.olat.core.gui.control.winmgr.AJAXFlags;

/**
 * 
 * Initial date: 28 mai 2018<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class DefaultGlobalSettings implements GlobalSettings {
	
	@Override
	public AJAXFlags getAjaxFlags() {
		return new EmptyAJAXFlags();
	}
	
	@Override
	public boolean isIdDivsForced() {
		return false;
	}
	
	private static class EmptyAJAXFlags extends AJAXFlags {
		
		public EmptyAJAXFlags() {
			super(null);
		}
		
		@Override
		public boolean isIframePostEnabled() {
			return false;
		}
	}
}
