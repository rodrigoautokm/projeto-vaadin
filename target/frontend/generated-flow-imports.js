export const addCssBlock = function(block, before = false) {
 const tpl = document.createElement('template');
 tpl.innerHTML = block;
 document.head[before ? 'insertBefore' : 'appendChild'](tpl.content, document.head.firstChild);
};

import { css, unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin';
import $cssFromFile_0 from 'Frontend/styles/styles.css?inline';
const $css_0 = typeof $cssFromFile_0  === 'string' ? unsafeCSS($cssFromFile_0) : $cssFromFile_0;
addCssBlock(`<style>${$css_0}</style>`);

import '@vaadin/app-layout/theme/lumo/vaadin-app-layout.js';
import '@vaadin/app-layout/theme/lumo/vaadin-drawer-toggle.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import '@vaadin/checkbox/theme/lumo/vaadin-checkbox.js';
import '@vaadin/combo-box/theme/lumo/vaadin-combo-box.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/context-menu/theme/lumo/vaadin-context-menu.js';
import '@vaadin/date-picker/theme/lumo/vaadin-date-picker.js';
import '@vaadin/dialog/theme/lumo/vaadin-dialog.js';
import '@vaadin/flow-frontend/comboBoxConnector.js';
import '@vaadin/flow-frontend/contextMenuConnector.js';
import '@vaadin/flow-frontend/contextMenuTargetConnector.js';
import '@vaadin/flow-frontend/datepickerConnector.js';
import '@vaadin/flow-frontend/dialogConnector.js';
import '@vaadin/flow-frontend/dndConnector-es6.js';
import '@vaadin/flow-frontend/flow-component-renderer.js';
import '@vaadin/flow-frontend/gridConnector.js';
import '@vaadin/flow-frontend/lit-renderer.ts';
import '@vaadin/flow-frontend/lumo-includes.ts';
import '@vaadin/flow-frontend/notificationConnector.js';
import '@vaadin/flow-frontend/vaadin-grid-flow-selection-column.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-column-group.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-column.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-sorter.js';
import '@vaadin/grid/theme/lumo/vaadin-grid.js';
import '@vaadin/horizontal-layout/theme/lumo/vaadin-horizontal-layout.js';
import '@vaadin/icon/vaadin-icon.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/multi-select-combo-box/theme/lumo/vaadin-multi-select-combo-box.js';
import '@vaadin/notification/theme/lumo/vaadin-notification.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/polymer-legacy-adapter/template-renderer.js';
import '@vaadin/tabs/theme/lumo/vaadin-tab.js';
import '@vaadin/tabs/theme/lumo/vaadin-tabs.js';
import '@vaadin/vaadin-lumo-styles/color.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/typography.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
let thisScript;
const elements = document.getElementsByTagName('script');
for (let i = 0; i < elements.length; i++) {
 const script = elements[i];
 if (script.getAttribute('type')=='module' && script.getAttribute('data-app-id') && !script['vaadin-bundle']) {
  thisScript = script;
  break;
 }
}
if (!thisScript) {
 throw new Error('Could not find the bundle script to identify the application id');
}
thisScript['vaadin-bundle'] = true;
if (!window.Vaadin.Flow.fallbacks) { window.Vaadin.Flow.fallbacks={}; }
const fallbacks = window.Vaadin.Flow.fallbacks;
fallbacks[thisScript.getAttribute('data-app-id')] = {}
fallbacks[thisScript.getAttribute('data-app-id')].loadFallback = function loadFallback() {
 return import('./generated-flow-imports-fallback.js');
}