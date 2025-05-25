import{c as Ji,D as oe,t as $,w as b,m as H,p as Xs,d as le,P as er,F as k,E as Ki,b as tr,f as ir,l as pe,r as sr,a as qe,g as rr,e as nr,h as ar,O as or,i as lr,s as dr,j as hr,k as g,n as ht,o as cr,M as ur,q as f,u as pr,v as K,x as mr,y as Ne,z as fr,A as _r,B as gr,C as u,G as ct,K as ut,H as pt,I as A,T as y,J as O,L as vr,N as yr,Q as Zi,R as mt,S as Me,U as ft,V as br,W as V,X as Fe,Y as Oe,Z as de,_ as Re,$ as Ar,a0 as xr,a1 as wr,a2 as Cr,a3 as De,a4 as Er,a5 as Sr,a6 as Ir,a7 as kr,a8 as Xi,a9 as Tr,aa as es,ab as _t,ac as gt,ad as Pr,ae as me,af as Br,ag as vt,ah as U,ai as te,aj as Mr,ak as Fr,al as ts,am as is,an as Or,ao as Rr,ap as Dr,aq as ss,ar as Lr,as as rs,at as zr,au as Nr,av as Vr,aw as $r,ax as Hr,ay as yt,az as Ur,aA as jr,aB as Gr,aC as Wr,aD as Yr,aE as qr,aF as Qr,aG as ns,aH as Jr,aI as Kr,aJ as Ve,aK as Zr,aL as Xr,aM as en}from"./generated-flow-imports.8d1e7eb3.js";import{i as p}from"./indexhtml.cd17e78d.js";/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Le=!(window.ShadyDOM&&window.ShadyDOM.inUse);let xe;function Tt(i){i&&i.shimcssproperties?xe=!1:xe=Le||Boolean(!navigator.userAgent.match(/AppleWebKit\/601|Edge\/15/)&&window.CSS&&CSS.supports&&CSS.supports("box-shadow","0 0 0 var(--foo)"))}let ae;window.ShadyCSS&&window.ShadyCSS.cssBuild!==void 0&&(ae=window.ShadyCSS.cssBuild);const as=Boolean(window.ShadyCSS&&window.ShadyCSS.disableRuntime);window.ShadyCSS&&window.ShadyCSS.nativeCss!==void 0?xe=window.ShadyCSS.nativeCss:window.ShadyCSS?(Tt(window.ShadyCSS),window.ShadyCSS=void 0):Tt(window.WebComponents&&window.WebComponents.flags);const bt=xe;/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/class Pt{constructor(){this.start=0,this.end=0,this.previous=null,this.parent=null,this.rules=null,this.parsedCssText="",this.cssText="",this.atRule=!1,this.type=0,this.keyframesName="",this.selector="",this.parsedSelector=""}}function os(i){return i=tn(i),ls(sn(i),i)}function tn(i){return i.replace(P.comments,"").replace(P.port,"")}function sn(i){let e=new Pt;e.start=0,e.end=i.length;let t=e;for(let s=0,r=i.length;s<r;s++)if(i[s]===hs){t.rules||(t.rules=[]);let a=t,l=a.rules[a.rules.length-1]||null;t=new Pt,t.start=s+1,t.parent=a,t.previous=l,a.rules.push(t)}else i[s]===cs&&(t.end=s+1,t=t.parent||e);return e}function ls(i,e){let t=e.substring(i.start,i.end-1);if(i.parsedCssText=i.cssText=t.trim(),i.parent){let r=i.previous?i.previous.end:i.parent.start;t=e.substring(r,i.start-1),t=rn(t),t=t.replace(P.multipleSpaces," "),t=t.substring(t.lastIndexOf(";")+1);let a=i.parsedSelector=i.selector=t.trim();i.atRule=a.indexOf(hn)===0,i.atRule?a.indexOf(dn)===0?i.type=B.MEDIA_RULE:a.match(P.keyframesRule)&&(i.type=B.KEYFRAMES_RULE,i.keyframesName=i.selector.split(P.multipleSpaces).pop()):a.indexOf(us)===0?i.type=B.MIXIN_RULE:i.type=B.STYLE_RULE}let s=i.rules;if(s)for(let r=0,a=s.length,l;r<a&&(l=s[r]);r++)ls(l,e);return i}function rn(i){return i.replace(/\\([0-9a-f]{1,6})\s/gi,function(){let e=arguments[1],t=6-e.length;for(;t--;)e="0"+e;return"\\"+e})}function ds(i,e,t=""){let s="";if(i.cssText||i.rules){let r=i.rules;if(r&&!nn(r))for(let a=0,l=r.length,n;a<l&&(n=r[a]);a++)s=ds(n,e,s);else s=e?i.cssText:an(i.cssText),s=s.trim(),s&&(s="  "+s+`
`)}return s&&(i.selector&&(t+=i.selector+" "+hs+`
`),t+=s,i.selector&&(t+=cs+`

`)),t}function nn(i){let e=i[0];return Boolean(e)&&Boolean(e.selector)&&e.selector.indexOf(us)===0}function an(i){return i=on(i),ln(i)}function on(i){return i.replace(P.customProp,"").replace(P.mixinProp,"")}function ln(i){return i.replace(P.mixinApply,"").replace(P.varApply,"")}const B={STYLE_RULE:1,KEYFRAMES_RULE:7,MEDIA_RULE:4,MIXIN_RULE:1e3},hs="{",cs="}",P={comments:/\/\*[^*]*\*+([^/*][^*]*\*+)*\//gim,port:/@import[^;]*;/gim,customProp:/(?:^[^;\-\s}]+)?--[^;{}]*?:[^{};]*?(?:[;\n]|$)/gim,mixinProp:/(?:^[^;\-\s}]+)?--[^;{}]*?:[^{};]*?{[^}]*?}(?:[;\n]|$)?/gim,mixinApply:/@apply\s*\(?[^);]*\)?\s*(?:[;\n]|$)?/gim,varApply:/[^;:]*?:[^;]*?var\([^;]*\)(?:[;\n]|$)?/gim,keyframesRule:/^@[^\s]*keyframes/,multipleSpaces:/\s+/g},us="--",dn="@media",hn="@";/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Qe=/(?:^|[;\s{]\s*)(--[\w-]*?)\s*:\s*(?:((?:'(?:\\'|.)*?'|"(?:\\"|.)*?"|\([^)]*?\)|[^};{])+)|\{([^}]*)\}(?:(?=[;\s}])|$))/gi,we=/(?:^|\W+)@apply\s*\(?([^);\n]*)\)?/gi,cn=/@media\s(.*)/;/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Bt=new Set,un="shady-unscoped";function pn(i){const e=i.textContent;if(!Bt.has(e)){Bt.add(e);const t=document.createElement("style");t.setAttribute("shady-unscoped",""),t.textContent=e,document.head.appendChild(t)}}function mn(i){return i.hasAttribute(un)}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/function Je(i,e){return i?(typeof i=="string"&&(i=os(i)),e&&ie(i,e),ds(i,bt)):""}function Mt(i){return!i.__cssRules&&i.textContent&&(i.__cssRules=os(i.textContent)),i.__cssRules||null}function ie(i,e,t,s){if(!i)return;let r=!1,a=i.type;if(s&&a===B.MEDIA_RULE){let n=i.selector.match(cn);n&&(window.matchMedia(n[1]).matches||(r=!0))}a===B.STYLE_RULE?e(i):t&&a===B.KEYFRAMES_RULE?t(i):a===B.MIXIN_RULE&&(r=!0);let l=i.rules;if(l&&!r)for(let n=0,o=l.length,d;n<o&&(d=l[n]);n++)ie(d,e,t,s)}function fn(i,e){let t=0;for(let s=e,r=i.length;s<r;s++)if(i[s]==="(")t++;else if(i[s]===")"&&--t===0)return s;return-1}function ps(i,e){let t=i.indexOf("var(");if(t===-1)return e(i,"","","");let s=fn(i,t+3),r=i.substring(t+4,s),a=i.substring(0,t),l=ps(i.substring(s+1),e),n=r.indexOf(",");if(n===-1)return e(a,r.trim(),"",l);let o=r.substring(0,n).trim(),d=r.substring(n+1).trim();return e(a,o,d,l)}window.ShadyDOM&&window.ShadyDOM.wrap;function _n(i){let e=i.localName,t="",s="";return e?e.indexOf("-")>-1?t=e:(s=e,t=i.getAttribute&&i.getAttribute("is")||""):(t=i.is,s=i.extends),{is:t,typeExtension:s}}function gn(i){const e=[],t=i.querySelectorAll("style");for(let s=0;s<t.length;s++){const r=t[s];mn(r)?Le||(pn(r),r.parentNode.removeChild(r)):(e.push(r.textContent),r.parentNode.removeChild(r))}return e.join("").trim()}const ms="css-build";function vn(i){if(ae!==void 0)return ae;if(i.__cssBuild===void 0){const e=i.getAttribute(ms);if(e)i.__cssBuild=e;else{const t=yn(i);t!==""&&bn(i),i.__cssBuild=t}}return i.__cssBuild||""}function Ft(i){return vn(i)!==""}function yn(i){const e=i.localName==="template"?i.content.firstChild:i.firstChild;if(e instanceof Comment){const t=e.textContent.trim().split(":");if(t[0]===ms)return t[1]}return""}function bn(i){const e=i.localName==="template"?i.content.firstChild:i.firstChild;e.parentNode.removeChild(e)}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/function Ke(i,e){for(let t in e)t===null?i.style.removeProperty(t):i.style.setProperty(t,e[t])}function fs(i,e){const t=window.getComputedStyle(i).getPropertyValue(e);return t?t.trim():""}function An(i){const e=we.test(i)||Qe.test(i);return we.lastIndex=0,Qe.lastIndex=0,e}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const xn=/;\s*/m,wn=/^\s*(initial)|(inherit)\s*$/,Ot=/\s*!important/,Ze="_-_";class Cn{constructor(){this._map={}}set(e,t){e=e.trim(),this._map[e]={properties:t,dependants:{}}}get(e){return e=e.trim(),this._map[e]||null}}let Ce=null;class C{constructor(){this._currentElement=null,this._measureElement=null,this._map=new Cn}detectMixin(e){return An(e)}gatherStyles(e){const t=gn(e.content);if(t){const s=document.createElement("style");return s.textContent=t,e.content.insertBefore(s,e.content.firstChild),s}return null}transformTemplate(e,t){e._gatheredStyle===void 0&&(e._gatheredStyle=this.gatherStyles(e));const s=e._gatheredStyle;return s?this.transformStyle(s,t):null}transformStyle(e,t=""){let s=Mt(e);return this.transformRules(s,t),e.textContent=Je(s),s}transformCustomStyle(e){let t=Mt(e);return ie(t,s=>{s.selector===":root"&&(s.selector="html"),this.transformRule(s)}),e.textContent=Je(t),t}transformRules(e,t){this._currentElement=t,ie(e,s=>{this.transformRule(s)}),this._currentElement=null}transformRule(e){e.cssText=this.transformCssText(e.parsedCssText,e),e.selector===":root"&&(e.selector=":host > *")}transformCssText(e,t){return e=e.replace(Qe,(s,r,a,l)=>this._produceCssProperties(s,r,a,l,t)),this._consumeCssProperties(e,t)}_getInitialValueForProperty(e){return this._measureElement||(this._measureElement=document.createElement("meta"),this._measureElement.setAttribute("apply-shim-measure",""),this._measureElement.style.all="initial",document.head.appendChild(this._measureElement)),window.getComputedStyle(this._measureElement).getPropertyValue(e)}_fallbacksFromPreviousRules(e){let t=e;for(;t.parent;)t=t.parent;const s={};let r=!1;return ie(t,a=>{r=r||a===e,!r&&a.selector===e.selector&&Object.assign(s,this._cssTextToMap(a.parsedCssText))}),s}_consumeCssProperties(e,t){let s=null;for(;s=we.exec(e);){let r=s[0],a=s[1],l=s.index,n=l+r.indexOf("@apply"),o=l+r.length,d=e.slice(0,n),h=e.slice(o),c=t?this._fallbacksFromPreviousRules(t):{};Object.assign(c,this._cssTextToMap(d));let m=this._atApplyToCssProperties(a,c);e=`${d}${m}${h}`,we.lastIndex=l+m.length}return e}_atApplyToCssProperties(e,t){e=e.replace(xn,"");let s=[],r=this._map.get(e);if(r||(this._map.set(e,{}),r=this._map.get(e)),r){this._currentElement&&(r.dependants[this._currentElement]=!0);let a,l,n;const o=r.properties;for(a in o)n=t&&t[a],l=[a,": var(",e,Ze,a],n&&l.push(",",n.replace(Ot,"")),l.push(")"),Ot.test(o[a])&&l.push(" !important"),s.push(l.join(""))}return s.join("; ")}_replaceInitialOrInherit(e,t){let s=wn.exec(t);return s&&(s[1]?t=this._getInitialValueForProperty(e):t="apply-shim-inherit"),t}_cssTextToMap(e,t=!1){let s=e.split(";"),r,a,l={};for(let n=0,o,d;n<s.length;n++)o=s[n],o&&(d=o.split(":"),d.length>1&&(r=d[0].trim(),a=d.slice(1).join(":"),t&&(a=this._replaceInitialOrInherit(r,a)),l[r]=a));return l}_invalidateMixinEntry(e){if(!!Ce)for(let t in e.dependants)t!==this._currentElement&&Ce(t)}_produceCssProperties(e,t,s,r,a){if(s&&ps(s,(q,R)=>{R&&this._map.get(R)&&(r=`@apply ${R};`)}),!r)return e;let l=this._consumeCssProperties(""+r,a),n=e.slice(0,e.indexOf("--")),o=this._cssTextToMap(l,!0),d=o,h=this._map.get(t),c=h&&h.properties;c?d=Object.assign(Object.create(c),o):this._map.set(t,d);let m=[],_,E,I=!1;for(_ in d)E=o[_],E===void 0&&(E="initial"),c&&!(_ in c)&&(I=!0),m.push(`${t}${Ze}${_}: ${E}`);return I&&this._invalidateMixinEntry(h),h&&(h.properties=d),s&&(n=`${e};${n}`),`${n}${m.join("; ")};`}}C.prototype.detectMixin=C.prototype.detectMixin;C.prototype.transformStyle=C.prototype.transformStyle;C.prototype.transformCustomStyle=C.prototype.transformCustomStyle;C.prototype.transformRules=C.prototype.transformRules;C.prototype.transformRule=C.prototype.transformRule;C.prototype.transformTemplate=C.prototype.transformTemplate;C.prototype._separator=Ze;Object.defineProperty(C.prototype,"invalidCallback",{get(){return Ce},set(i){Ce=i}});/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Xe={};/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Ee="_applyShimCurrentVersion",G="_applyShimNextVersion",Se="_applyShimValidatingVersion",En=Promise.resolve();function Sn(i){let e=Xe[i];e&&In(e)}function In(i){i[Ee]=i[Ee]||0,i[Se]=i[Se]||0,i[G]=(i[G]||0)+1}function _s(i){return i[Ee]===i[G]}function kn(i){return!_s(i)&&i[Se]===i[G]}function Tn(i){i[Se]=i[G],i._validating||(i._validating=!0,En.then(function(){i[Ee]=i[G],i._validating=!1}))}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/let $e=null,Rt=window.HTMLImports&&window.HTMLImports.whenReady||null,He;function Dt(i){requestAnimationFrame(function(){Rt?Rt(i):($e||($e=new Promise(e=>{He=e}),document.readyState==="complete"?He():document.addEventListener("readystatechange",()=>{document.readyState==="complete"&&He()})),$e.then(function(){i&&i()}))})}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Lt="__seenByShadyCSS",fe="__shadyCSSCachedStyle";let Ie=null,se=null;class F{constructor(){this.customStyles=[],this.enqueued=!1,Dt(()=>{window.ShadyCSS.flushCustomStyles&&window.ShadyCSS.flushCustomStyles()})}enqueueDocumentValidation(){this.enqueued||!se||(this.enqueued=!0,Dt(se))}addCustomStyle(e){e[Lt]||(e[Lt]=!0,this.customStyles.push(e),this.enqueueDocumentValidation())}getStyleForCustomStyle(e){if(e[fe])return e[fe];let t;return e.getStyle?t=e.getStyle():t=e,t}processStyles(){const e=this.customStyles;for(let t=0;t<e.length;t++){const s=e[t];if(s[fe])continue;const r=this.getStyleForCustomStyle(s);if(r){const a=r.__appliedElement||r;Ie&&Ie(a),s[fe]=a}}return e}}F.prototype.addCustomStyle=F.prototype.addCustomStyle;F.prototype.getStyleForCustomStyle=F.prototype.getStyleForCustomStyle;F.prototype.processStyles=F.prototype.processStyles;Object.defineProperties(F.prototype,{transformCallback:{get(){return Ie},set(i){Ie=i}},validateCallback:{get(){return se},set(i){let e=!1;se||(e=!0),se=i,e&&this.enqueueDocumentValidation()}}});/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const ee=new C;class Pn{constructor(){this.customStyleInterface=null,ee.invalidCallback=Sn}ensure(){this.customStyleInterface||window.ShadyCSS.CustomStyleInterface&&(this.customStyleInterface=window.ShadyCSS.CustomStyleInterface,this.customStyleInterface.transformCallback=e=>{ee.transformCustomStyle(e)},this.customStyleInterface.validateCallback=()=>{requestAnimationFrame(()=>{this.customStyleInterface.enqueued&&this.flushCustomStyles()})})}prepareTemplate(e,t){if(this.ensure(),Ft(e))return;Xe[t]=e;let s=ee.transformTemplate(e,t);e._styleAst=s}flushCustomStyles(){if(this.ensure(),!this.customStyleInterface)return;let e=this.customStyleInterface.processStyles();if(!!this.customStyleInterface.enqueued){for(let t=0;t<e.length;t++){let s=e[t],r=this.customStyleInterface.getStyleForCustomStyle(s);r&&ee.transformCustomStyle(r)}this.customStyleInterface.enqueued=!1}}styleSubtree(e,t){if(this.ensure(),t&&Ke(e,t),e.shadowRoot){this.styleElement(e);let s=e.shadowRoot.children||e.shadowRoot.childNodes;for(let r=0;r<s.length;r++)this.styleSubtree(s[r])}else{let s=e.children||e.childNodes;for(let r=0;r<s.length;r++)this.styleSubtree(s[r])}}styleElement(e){this.ensure();let{is:t}=_n(e),s=Xe[t];if(!(s&&Ft(s))&&s&&!_s(s)){kn(s)||(this.prepareTemplate(s,t),Tn(s));let r=e.shadowRoot;if(r){let a=r.querySelector("style");a&&(a.__cssRules=s._styleAst,a.textContent=Je(s._styleAst))}}}styleDocument(e){this.ensure(),this.styleSubtree(document.body,e)}}if(!window.ShadyCSS||!window.ShadyCSS.ScopingShim){const i=new Pn;let e=window.ShadyCSS&&window.ShadyCSS.CustomStyleInterface;window.ShadyCSS={prepareTemplate(t,s,r){i.flushCustomStyles(),i.prepareTemplate(t,s)},prepareTemplateStyles(t,s,r){window.ShadyCSS.prepareTemplate(t,s,r)},prepareTemplateDom(t,s){},styleSubtree(t,s){i.flushCustomStyles(),i.styleSubtree(t,s)},styleElement(t){i.flushCustomStyles(),i.styleElement(t)},styleDocument(t){i.flushCustomStyles(),i.styleDocument(t)},getComputedStyleValue(t,s){return fs(t,s)},flushCustomStyles(){i.flushCustomStyles()},nativeCss:bt,nativeShadow:Le,cssBuild:ae,disableRuntime:as},e&&(window.ShadyCSS.CustomStyleInterface=e)}window.ShadyCSS.ApplyShim=ee;/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/let At=typeof document.head.style.touchAction=="string",ke="__polymerGestures",ye="__polymerGesturesHandled",et="__polymerGesturesTouchAction",zt=25,Nt=5,Bn=2,Mn=2500,gs=["mousedown","mousemove","mouseup","click"],Fn=[0,1,4,2],On=function(){try{return new MouseEvent("test",{buttons:1}).buttons===1}catch{return!1}}();function xt(i){return gs.indexOf(i)>-1}let wt=!1;(function(){try{let i=Object.defineProperty({},"passive",{get(){wt=!0}});window.addEventListener("test",null,i),window.removeEventListener("test",null,i)}catch{}})();function vs(i){if(!(xt(i)||i==="touchend")&&At&&wt&&Xs)return{passive:!0}}let ys=navigator.userAgent.match(/iP(?:[oa]d|hone)|Android/);const tt=[],Rn={button:!0,input:!0,keygen:!0,meter:!0,output:!0,textarea:!0,progress:!0,select:!0},Dn={button:!0,command:!0,fieldset:!0,input:!0,keygen:!0,optgroup:!0,option:!0,select:!0,textarea:!0};function Ln(i){return Rn[i.localName]||!1}function zn(i){let e=Array.prototype.slice.call(i.labels||[]);if(!e.length){e=[];try{let t=i.getRootNode();if(i.id){let s=t.querySelectorAll(`label[for = '${i.id}']`);for(let r=0;r<s.length;r++)e.push(s[r])}}catch{}}return e}let Vt=function(i){let e=i.sourceCapabilities;if(!(e&&!e.firesTouchEvents)&&(i[ye]={skip:!0},i.type==="click")){let t=!1,s=ze(i);for(let r=0;r<s.length;r++){if(s[r].nodeType===Node.ELEMENT_NODE){if(s[r].localName==="label")tt.push(s[r]);else if(Ln(s[r])){let a=zn(s[r]);for(let l=0;l<a.length;l++)t=t||tt.indexOf(a[l])>-1}}if(s[r]===x.mouse.target)return}if(t)return;i.preventDefault(),i.stopPropagation()}};function $t(i){let e=ys?["click"]:gs;for(let t=0,s;t<e.length;t++)s=e[t],i?(tt.length=0,document.addEventListener(s,Vt,!0)):document.removeEventListener(s,Vt,!0)}function Nn(i){if(!Ji)return;x.mouse.mouseIgnoreJob||$t(!0);let e=function(){$t(),x.mouse.target=null,x.mouse.mouseIgnoreJob=null};x.mouse.target=ze(i)[0],x.mouse.mouseIgnoreJob=oe.debounce(x.mouse.mouseIgnoreJob,$.after(Mn),e)}function L(i){let e=i.type;if(!xt(e))return!1;if(e==="mousemove"){let t=i.buttons===void 0?1:i.buttons;return i instanceof window.MouseEvent&&!On&&(t=Fn[i.which]||0),Boolean(t&1)}else return(i.button===void 0?0:i.button)===0}function Vn(i){if(i.type==="click"){if(i.detail===0)return!0;let e=M(i);if(!e.nodeType||e.nodeType!==Node.ELEMENT_NODE)return!0;let t=e.getBoundingClientRect(),s=i.pageX,r=i.pageY;return!(s>=t.left&&s<=t.right&&r>=t.top&&r<=t.bottom)}return!1}let x={mouse:{target:null,mouseIgnoreJob:null},touch:{x:0,y:0,id:-1,scrollDecided:!1}};function $n(i){let e="auto",t=ze(i);for(let s=0,r;s<t.length;s++)if(r=t[s],r[et]){e=r[et];break}return e}function bs(i,e,t){i.movefn=e,i.upfn=t,document.addEventListener("mousemove",e),document.addEventListener("mouseup",t)}function j(i){document.removeEventListener("mousemove",i.movefn),document.removeEventListener("mouseup",i.upfn),i.movefn=null,i.upfn=null}Ji&&document.addEventListener("touchend",Nn,wt?{passive:!0}:!1);const ze=window.ShadyDOM&&window.ShadyDOM.noPatch?window.ShadyDOM.composedPath:i=>i.composedPath&&i.composedPath()||[],he={},D=[];function Hn(i,e){let t=document.elementFromPoint(i,e),s=t;for(;s&&s.shadowRoot&&!window.ShadyDOM;){let r=s;if(s=s.shadowRoot.elementFromPoint(i,e),r===s)break;s&&(t=s)}return t}function M(i){const e=ze(i);return e.length>0?e[0]:i.target}function As(i){let e,t=i.type,r=i.currentTarget[ke];if(!r)return;let a=r[t];if(!!a){if(!i[ye]&&(i[ye]={},t.slice(0,5)==="touch")){i=i;let l=i.changedTouches[0];if(t==="touchstart"&&i.touches.length===1&&(x.touch.id=l.identifier),x.touch.id!==l.identifier)return;At||(t==="touchstart"||t==="touchmove")&&Un(i)}if(e=i[ye],!e.skip){for(let l=0,n;l<D.length;l++)n=D[l],a[n.name]&&!e[n.name]&&n.flow&&n.flow.start.indexOf(i.type)>-1&&n.reset&&n.reset();for(let l=0,n;l<D.length;l++)n=D[l],a[n.name]&&!e[n.name]&&(e[n.name]=!0,n[t](i))}}}function Un(i){let e=i.changedTouches[0],t=i.type;if(t==="touchstart")x.touch.x=e.clientX,x.touch.y=e.clientY,x.touch.scrollDecided=!1;else if(t==="touchmove"){if(x.touch.scrollDecided)return;x.touch.scrollDecided=!0;let s=$n(i),r=!1,a=Math.abs(x.touch.x-e.clientX),l=Math.abs(x.touch.y-e.clientY);i.cancelable&&(s==="none"?r=!0:s==="pan-x"?r=l>a:s==="pan-y"&&(r=a>l)),r?i.preventDefault():Te("track")}}function jn(i,e,t){return he[e]?(Wn(i,e,t),!0):!1}function Gn(i,e,t){return he[e]?(Yn(i,e,t),!0):!1}function Wn(i,e,t){let s=he[e],r=s.deps,a=s.name,l=i[ke];l||(i[ke]=l={});for(let n=0,o,d;n<r.length;n++)o=r[n],!(ys&&xt(o)&&o!=="click")&&(d=l[o],d||(l[o]=d={_count:0}),d._count===0&&i.addEventListener(o,As,vs(o)),d[a]=(d[a]||0)+1,d._count=(d._count||0)+1);i.addEventListener(e,t),s.touchAction&&xs(i,s.touchAction)}function Yn(i,e,t){let s=he[e],r=s.deps,a=s.name,l=i[ke];if(l)for(let n=0,o,d;n<r.length;n++)o=r[n],d=l[o],d&&d[a]&&(d[a]=(d[a]||1)-1,d._count=(d._count||1)-1,d._count===0&&i.removeEventListener(o,As,vs(o)));i.removeEventListener(e,t)}function Ct(i){D.push(i);for(let e=0;e<i.emits.length;e++)he[i.emits[e]]=i}function qn(i){for(let e=0,t;e<D.length;e++){t=D[e];for(let s=0,r;s<t.emits.length;s++)if(r=t.emits[s],r===i)return t}return null}function xs(i,e){At&&i instanceof HTMLElement&&H.run(()=>{i.style.touchAction=e}),i[et]=e}function Et(i,e,t){let s=new Event(e,{bubbles:!0,cancelable:!0,composed:!0});if(s.detail=t,b(i).dispatchEvent(s),s.defaultPrevented){let r=t.preventer||t.sourceEvent;r&&r.preventDefault&&r.preventDefault()}}function Te(i){let e=qn(i);e.info&&(e.info.prevent=!0)}Ct({name:"downup",deps:["mousedown","touchstart","touchend"],flow:{start:["mousedown","touchstart"],end:["mouseup","touchend"]},emits:["down","up"],info:{movefn:null,upfn:null},reset:function(){j(this.info)},mousedown:function(i){if(!L(i))return;let e=M(i),t=this,s=function(l){L(l)||(Z("up",e,l),j(t.info))},r=function(l){L(l)&&Z("up",e,l),j(t.info)};bs(this.info,s,r),Z("down",e,i)},touchstart:function(i){Z("down",M(i),i.changedTouches[0],i)},touchend:function(i){Z("up",M(i),i.changedTouches[0],i)}});function Z(i,e,t,s){!e||Et(e,i,{x:t.clientX,y:t.clientY,sourceEvent:t,preventer:s,prevent:function(r){return Te(r)}})}Ct({name:"track",touchAction:"none",deps:["mousedown","touchstart","touchmove","touchend"],flow:{start:["mousedown","touchstart"],end:["mouseup","touchend"]},emits:["track"],info:{x:0,y:0,state:"start",started:!1,moves:[],addMove:function(i){this.moves.length>Bn&&this.moves.shift(),this.moves.push(i)},movefn:null,upfn:null,prevent:!1},reset:function(){this.info.state="start",this.info.started=!1,this.info.moves=[],this.info.x=0,this.info.y=0,this.info.prevent=!1,j(this.info)},mousedown:function(i){if(!L(i))return;let e=M(i),t=this,s=function(l){let n=l.clientX,o=l.clientY;Ht(t.info,n,o)&&(t.info.state=t.info.started?l.type==="mouseup"?"end":"track":"start",t.info.state==="start"&&Te("tap"),t.info.addMove({x:n,y:o}),L(l)||(t.info.state="end",j(t.info)),e&&Ue(t.info,e,l),t.info.started=!0)},r=function(l){t.info.started&&s(l),j(t.info)};bs(this.info,s,r),this.info.x=i.clientX,this.info.y=i.clientY},touchstart:function(i){let e=i.changedTouches[0];this.info.x=e.clientX,this.info.y=e.clientY},touchmove:function(i){let e=M(i),t=i.changedTouches[0],s=t.clientX,r=t.clientY;Ht(this.info,s,r)&&(this.info.state==="start"&&Te("tap"),this.info.addMove({x:s,y:r}),Ue(this.info,e,t),this.info.state="track",this.info.started=!0)},touchend:function(i){let e=M(i),t=i.changedTouches[0];this.info.started&&(this.info.state="end",this.info.addMove({x:t.clientX,y:t.clientY}),Ue(this.info,e,t))}});function Ht(i,e,t){if(i.prevent)return!1;if(i.started)return!0;let s=Math.abs(i.x-e),r=Math.abs(i.y-t);return s>=Nt||r>=Nt}function Ue(i,e,t){if(!e)return;let s=i.moves[i.moves.length-2],r=i.moves[i.moves.length-1],a=r.x-i.x,l=r.y-i.y,n,o=0;s&&(n=r.x-s.x,o=r.y-s.y),Et(e,"track",{state:i.state,x:t.clientX,y:t.clientY,dx:a,dy:l,ddx:n,ddy:o,sourceEvent:t,hover:function(){return Hn(t.clientX,t.clientY)}})}Ct({name:"tap",deps:["mousedown","click","touchstart","touchend"],flow:{start:["mousedown","touchstart"],end:["click","touchend"]},emits:["tap"],info:{x:NaN,y:NaN,prevent:!1},reset:function(){this.info.x=NaN,this.info.y=NaN,this.info.prevent=!1},mousedown:function(i){L(i)&&(this.info.x=i.clientX,this.info.y=i.clientY)},click:function(i){L(i)&&Ut(this.info,i)},touchstart:function(i){const e=i.changedTouches[0];this.info.x=e.clientX,this.info.y=e.clientY},touchend:function(i){Ut(this.info,i.changedTouches[0],i)}});function Ut(i,e,t){let s=Math.abs(e.clientX-i.x),r=Math.abs(e.clientY-i.y),a=M(t||e);!a||Dn[a.localName]&&a.hasAttribute("disabled")||(isNaN(s)||isNaN(r)||s<=zt&&r<=zt||Vn(e))&&(i.prevent||Et(a,"tap",{x:e.clientX,y:e.clientY,sourceEvent:e,preventer:t}))}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const ws=le(i=>{class e extends i{_addEventListenerToNode(s,r,a){jn(s,r,a)||super._addEventListenerToNode(s,r,a)}_removeEventListenerFromNode(s,r,a){Gn(s,r,a)||super._removeEventListenerFromNode(s,r,a)}}return e});/**
 * @fileoverview
 * @suppress {checkPrototypalTypes}
 * @license Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
 * This code may only be used under the BSD style license found at
 * http://polymer.github.io/LICENSE.txt The complete set of authors may be found
 * at http://polymer.github.io/AUTHORS.txt The complete set of contributors may
 * be found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by
 * Google as part of the polymer project is also subject to an additional IP
 * rights grant found at http://polymer.github.io/PATENTS.txt
 */const Qn=/:host\(:dir\((ltr|rtl)\)\)/g,Jn=':host([dir="$1"])',Kn=/([\s\w-#\.\[\]\*]*):dir\((ltr|rtl)\)/g,Zn=':host([dir="$2"]) $1',Xn=/:dir\((?:ltr|rtl)\)/,jt=Boolean(window.ShadyDOM&&window.ShadyDOM.inUse),re=[];let ne=null,St="";function Cs(){St=document.documentElement.getAttribute("dir")}function Es(i){i.__autoDirOptOut||i.setAttribute("dir",St)}function Ss(){Cs(),St=document.documentElement.getAttribute("dir");for(let i=0;i<re.length;i++)Es(re[i])}function ea(){ne&&ne.takeRecords().length&&Ss()}const ta=le(i=>{jt||ne||(Cs(),ne=new MutationObserver(Ss),ne.observe(document.documentElement,{attributes:!0,attributeFilter:["dir"]}));const e=er(i);class t extends e{static _processStyleText(r,a){return r=e._processStyleText.call(this,r,a),!jt&&Xn.test(r)&&(r=this._replaceDirInCssText(r),this.__activateDir=!0),r}static _replaceDirInCssText(r){let a=r;return a=a.replace(Qn,Jn),a=a.replace(Kn,Zn),a}constructor(){super(),this.__autoDirOptOut=!1}ready(){super.ready(),this.__autoDirOptOut=this.hasAttribute("dir")}connectedCallback(){e.prototype.connectedCallback&&super.connectedCallback(),this.constructor.__activateDir&&(ea(),re.push(this),Es(this))}disconnectedCallback(){if(e.prototype.disconnectedCallback&&super.disconnectedCallback(),this.constructor.__activateDir){const r=re.indexOf(this);r>-1&&re.splice(r,1)}}}return t.__activateDir=!1,t});/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/function Gt(){document.body.removeAttribute("unresolved")}document.readyState==="interactive"||document.readyState==="complete"?Gt():window.addEventListener("DOMContentLoaded",Gt);/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const z=Element.prototype,ia=z.matches||z.matchesSelector||z.mozMatchesSelector||z.msMatchesSelector||z.oMatchesSelector||z.webkitMatchesSelector,Is=function(i,e){return ia.call(i,e)};class v{constructor(e){window.ShadyDOM&&window.ShadyDOM.inUse&&window.ShadyDOM.patch(e),this.node=e}observeNodes(e){return new k(this.node,e)}unobserveNodes(e){e.disconnect()}notifyObserver(){}deepContains(e){if(b(this.node).contains(e))return!0;let t=e,s=e.ownerDocument;for(;t&&t!==s&&t!==this.node;)t=b(t).parentNode||b(t).host;return t===this.node}getOwnerRoot(){return b(this.node).getRootNode()}getDistributedNodes(){return this.node.localName==="slot"?b(this.node).assignedNodes({flatten:!0}):[]}getDestinationInsertionPoints(){let e=[],t=b(this.node).assignedSlot;for(;t;)e.push(t),t=b(t).assignedSlot;return e}importNode(e,t){let s=this.node instanceof Document?this.node:this.node.ownerDocument;return b(s).importNode(e,t)}getEffectiveChildNodes(){return k.getFlattenedNodes(this.node)}queryDistributedElements(e){let t=this.getEffectiveChildNodes(),s=[];for(let r=0,a=t.length,l;r<a&&(l=t[r]);r++)l.nodeType===Node.ELEMENT_NODE&&Is(l,e)&&s.push(l);return s}get activeElement(){let e=this.node;return e._activeElement!==void 0?e._activeElement:e.activeElement}}function sa(i,e){for(let t=0;t<e.length;t++){let s=e[t];i[s]=function(){return this.node[s].apply(this.node,arguments)}}}function Wt(i,e){for(let t=0;t<e.length;t++){let s=e[t];Object.defineProperty(i,s,{get:function(){return this.node[s]},configurable:!0})}}function ra(i,e){for(let t=0;t<e.length;t++){let s=e[t];Object.defineProperty(i,s,{get:function(){return this.node[s]},set:function(r){this.node[s]=r},configurable:!0})}}class it{constructor(e){this.event=e}get rootTarget(){return this.path[0]}get localTarget(){return this.event.target}get path(){return this.event.composedPath()}}v.prototype.cloneNode;v.prototype.appendChild;v.prototype.insertBefore;v.prototype.removeChild;v.prototype.replaceChild;v.prototype.setAttribute;v.prototype.removeAttribute;v.prototype.querySelector;v.prototype.querySelectorAll;v.prototype.parentNode;v.prototype.firstChild;v.prototype.lastChild;v.prototype.nextSibling;v.prototype.previousSibling;v.prototype.firstElementChild;v.prototype.lastElementChild;v.prototype.nextElementSibling;v.prototype.previousElementSibling;v.prototype.childNodes;v.prototype.children;v.prototype.classList;v.prototype.textContent;v.prototype.innerHTML;let st=v;if(window.ShadyDOM&&window.ShadyDOM.inUse&&window.ShadyDOM.noPatch&&window.ShadyDOM.Wrapper){class i extends window.ShadyDOM.Wrapper{}Object.getOwnPropertyNames(v.prototype).forEach(e=>{e!="activeElement"&&(i.prototype[e]=v.prototype[e])}),Wt(i.prototype,["classList"]),st=i,Object.defineProperties(it.prototype,{localTarget:{get(){const e=this.event.currentTarget,t=e&&w(e).getOwnerRoot(),s=this.path;for(let r=0;r<s.length;r++){const a=s[r];if(w(a).getOwnerRoot()===t)return a}},configurable:!0},path:{get(){return window.ShadyDOM.composedPath(this.event)},configurable:!0}})}else sa(v.prototype,["cloneNode","appendChild","insertBefore","removeChild","replaceChild","setAttribute","removeAttribute","querySelector","querySelectorAll","attachShadow"]),Wt(v.prototype,["parentNode","firstChild","lastChild","nextSibling","previousSibling","firstElementChild","lastElementChild","nextElementSibling","previousElementSibling","childNodes","children","classList","shadowRoot"]),ra(v.prototype,["textContent","innerHTML","className"]);const w=function(i){if(i=i||document,i instanceof st||i instanceof it)return i;let e=i.__domApi;return e||(i instanceof Event?e=new it(i):e=new st(i),i.__domApi=e),e};/**
@license
Copyright (c) 2019 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const je=window.ShadyDOM,Yt=window.ShadyCSS;function qt(i,e){return b(i).getRootNode()===e}function na(i,e=!1){if(!je||!Yt||!je.handlesDynamicScoping)return null;const t=Yt.ScopingShim;if(!t)return null;const s=t.scopeForNode(i),r=b(i).getRootNode(),a=l=>{if(!qt(l,r))return;const n=Array.from(je.nativeMethods.querySelectorAll.call(l,"*"));n.push(l);for(let o=0;o<n.length;o++){const d=n[o];if(!qt(d,r))continue;const h=t.currentScopeForNode(d);h!==s&&(h!==""&&t.unscopeNode(d,h),t.scopeNode(d,s))}};if(a(i),e){const l=new MutationObserver(n=>{for(let o=0;o<n.length;o++){const d=n[o];for(let h=0;h<d.addedNodes.length;h++){const c=d.addedNodes[h];c.nodeType===Node.ELEMENT_NODE&&a(c)}}});return l.observe(i,{childList:!0,subtree:!0}),l}else return null}/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const _e="disable-upgrade";let aa=window.ShadyCSS;const ks=le(i=>{const e=ws(Ki(i)),t=tr?e:ta(e),s=ir(t),r={x:"pan-x",y:"pan-y",none:"none",all:"auto"};class a extends t{constructor(){super(),this.isAttached,this.__boundListeners,this._debouncers,this.__isUpgradeDisabled,this.__needsAttributesAtConnected,this._legacyForceObservedAttributes}static get importMeta(){return this.prototype.importMeta}created(){}__attributeReaction(n,o,d){(this.__dataAttributes&&this.__dataAttributes[n]||n===_e)&&this.attributeChangedCallback(n,o,d,null)}setAttribute(n,o){if(pe&&!this._legacyForceObservedAttributes){const d=this.getAttribute(n);super.setAttribute(n,o),this.__attributeReaction(n,d,String(o))}else super.setAttribute(n,o)}removeAttribute(n){if(pe&&!this._legacyForceObservedAttributes){const o=this.getAttribute(n);super.removeAttribute(n),this.__attributeReaction(n,o,null)}else super.removeAttribute(n)}static get observedAttributes(){return pe&&!this.prototype._legacyForceObservedAttributes?(this.hasOwnProperty(JSCompiler_renameProperty("__observedAttributes",this))||(this.__observedAttributes=[],sr(this.prototype)),this.__observedAttributes):s.call(this).concat(_e)}_enableProperties(){this.__isUpgradeDisabled||super._enableProperties()}_canApplyPropertyDefault(n){return super._canApplyPropertyDefault(n)&&!(this.__isUpgradeDisabled&&this._isPropertyPending(n))}connectedCallback(){this.__needsAttributesAtConnected&&this._takeAttributes(),this.__isUpgradeDisabled||(super.connectedCallback(),this.isAttached=!0,this.attached())}attached(){}disconnectedCallback(){this.__isUpgradeDisabled||(super.disconnectedCallback(),this.isAttached=!1,this.detached())}detached(){}attributeChangedCallback(n,o,d,h){o!==d&&(n==_e?this.__isUpgradeDisabled&&d==null&&(this._initializeProperties(),this.__isUpgradeDisabled=!1,b(this).isConnected&&this.connectedCallback()):(super.attributeChangedCallback(n,o,d,h),this.attributeChanged(n,o,d)))}attributeChanged(n,o,d){}_initializeProperties(){if(qe&&this.hasAttribute(_e))this.__isUpgradeDisabled=!0;else{let n=Object.getPrototypeOf(this);n.hasOwnProperty(JSCompiler_renameProperty("__hasRegisterFinished",n))||(this._registered(),n.__hasRegisterFinished=!0),super._initializeProperties(),this.root=this,this.created(),pe&&!this._legacyForceObservedAttributes&&(this.hasAttributes()?this._takeAttributes():this.parentNode||(this.__needsAttributesAtConnected=!0)),this._applyListeners()}}_takeAttributes(){const n=this.attributes;for(let o=0,d=n.length;o<d;o++){const h=n[o];this.__attributeReaction(h.name,null,h.value)}}_registered(){}ready(){this._ensureAttributes(),super.ready()}_ensureAttributes(){}_applyListeners(){}serialize(n){return this._serializeValue(n)}deserialize(n,o){return this._deserializeValue(n,o)}reflectPropertyToAttribute(n,o,d){this._propertyToAttribute(n,o,d)}serializeValueToAttribute(n,o,d){this._valueToNodeAttribute(d||this,n,o)}extend(n,o){if(!(n&&o))return n||o;let d=Object.getOwnPropertyNames(o);for(let h=0,c;h<d.length&&(c=d[h]);h++){let m=Object.getOwnPropertyDescriptor(o,c);m&&Object.defineProperty(n,c,m)}return n}mixin(n,o){for(let d in o)n[d]=o[d];return n}chainObject(n,o){return n&&o&&n!==o&&(n.__proto__=o),n}instanceTemplate(n){let o=this.constructor._contentForTemplate(n);return document.importNode(o,!0)}fire(n,o,d){d=d||{},o=o==null?{}:o;let h=new Event(n,{bubbles:d.bubbles===void 0?!0:d.bubbles,cancelable:Boolean(d.cancelable),composed:d.composed===void 0?!0:d.composed});h.detail=o;let c=d.node||this;return b(c).dispatchEvent(h),h}listen(n,o,d){n=n||this;let h=this.__boundListeners||(this.__boundListeners=new WeakMap),c=h.get(n);c||(c={},h.set(n,c));let m=o+d;c[m]||(c[m]=this._addMethodEventListenerToNode(n,o,d,this))}unlisten(n,o,d){n=n||this;let h=this.__boundListeners&&this.__boundListeners.get(n),c=o+d,m=h&&h[c];m&&(this._removeEventListenerFromNode(n,o,m),h[c]=null)}setScrollDirection(n,o){xs(o||this,r[n]||"auto")}$$(n){return this.root.querySelector(n)}get domHost(){let n=b(this).getRootNode();return n instanceof DocumentFragment?n.host:n}distributeContent(){const o=w(this);window.ShadyDOM&&o.shadowRoot&&ShadyDOM.flush()}getEffectiveChildNodes(){return w(this).getEffectiveChildNodes()}queryDistributedElements(n){return w(this).queryDistributedElements(n)}getEffectiveChildren(){return this.getEffectiveChildNodes().filter(function(o){return o.nodeType===Node.ELEMENT_NODE})}getEffectiveTextContent(){let n=this.getEffectiveChildNodes(),o=[];for(let d=0,h;h=n[d];d++)h.nodeType!==Node.COMMENT_NODE&&o.push(h.textContent);return o.join("")}queryEffectiveChildren(n){let o=this.queryDistributedElements(n);return o&&o[0]}queryAllEffectiveChildren(n){return this.queryDistributedElements(n)}getContentChildNodes(n){let o=this.root.querySelector(n||"slot");return o?w(o).getDistributedNodes():[]}getContentChildren(n){return this.getContentChildNodes(n).filter(function(d){return d.nodeType===Node.ELEMENT_NODE})}isLightDescendant(n){const o=this;return o!==n&&b(o).contains(n)&&b(o).getRootNode()===b(n).getRootNode()}isLocalDescendant(n){return this.root===b(n).getRootNode()}scopeSubtree(n,o=!1){return na(n,o)}getComputedStyleValue(n){return aa.getComputedStyleValue(this,n)}debounce(n,o,d){return this._debouncers=this._debouncers||{},this._debouncers[n]=oe.debounce(this._debouncers[n],d>0?$.after(d):H,o.bind(this))}isDebouncerActive(n){this._debouncers=this._debouncers||{};let o=this._debouncers[n];return!!(o&&o.isActive())}flushDebouncer(n){this._debouncers=this._debouncers||{};let o=this._debouncers[n];o&&o.flush()}cancelDebouncer(n){this._debouncers=this._debouncers||{};let o=this._debouncers[n];o&&o.cancel()}async(n,o){return o>0?$.run(n.bind(this),o):~H.run(n.bind(this))}cancelAsync(n){n<0?H.cancel(~n):$.cancel(n)}create(n,o){let d=document.createElement(n);if(o)if(d.setProperties)d.setProperties(o);else for(let h in o)d[h]=o[h];return d}elementMatches(n,o){return Is(o||this,n)}toggleAttribute(n,o){let d=this;return arguments.length===3&&(d=arguments[2]),arguments.length==1&&(o=!d.hasAttribute(n)),o?(b(d).setAttribute(n,""),!0):(b(d).removeAttribute(n),!1)}toggleClass(n,o,d){d=d||this,arguments.length==1&&(o=!d.classList.contains(n)),o?d.classList.add(n):d.classList.remove(n)}transform(n,o){o=o||this,o.style.webkitTransform=n,o.style.transform=n}translate3d(n,o,d,h){h=h||this,this.transform("translate3d("+n+","+o+","+d+")",h)}arrayDelete(n,o){let d;if(Array.isArray(n)){if(d=n.indexOf(o),d>=0)return n.splice(d,1)}else if(d=rr(this,n).indexOf(o),d>=0)return this.splice(n,d,1);return null}_logger(n,o){switch(Array.isArray(o)&&o.length===1&&Array.isArray(o[0])&&(o=o[0]),n){case"log":case"warn":case"error":console[n](...o)}}_log(...n){this._logger("log",n)}_warn(...n){this._logger("warn",n)}_error(...n){this._logger("error",n)}_logf(n,...o){return["[%s::%s]",this.is,n,...o]}}return a.prototype.is="",a});/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const oa={attached:!0,detached:!0,ready:!0,created:!0,beforeRegister:!0,registered:!0,attributeChanged:!0,listeners:!0,hostAttributes:!0},Ts={attached:!0,detached:!0,ready:!0,created:!0,beforeRegister:!0,registered:!0,attributeChanged:!0,behaviors:!0,_noAccessors:!0},la=Object.assign({listeners:!0,hostAttributes:!0,properties:!0,observers:!0},Ts);function da(i,e,t){const s=i._noAccessors,r=Object.getOwnPropertyNames(i);for(let a=0;a<r.length;a++){let l=r[a];if(!(l in t))if(s)e[l]=i[l];else{let n=Object.getOwnPropertyDescriptor(i,l);n&&(n.configurable=!0,Object.defineProperty(e,l,n))}}}function ha(i,e,t){for(let s=0;s<e.length;s++)Ps(i,e[s],t,la)}function Ps(i,e,t,s){da(e,i,s);for(let r in oa)e[r]&&(t[r]=t[r]||[],t[r].push(e[r]))}function Bs(i,e,t){e=e||[];for(let s=i.length-1;s>=0;s--){let r=i[s];r?Array.isArray(r)?Bs(r,e):e.indexOf(r)<0&&(!t||t.indexOf(r)<0)&&e.unshift(r):console.warn("behavior is null, check for missing or 404 import")}return e}function Qt(i,e){for(const t in e){const s=i[t],r=e[t];!("value"in r)&&s&&"value"in s?i[t]=Object.assign({value:s.value},r):i[t]=r}}const Jt=ks(HTMLElement);function ca(i,e,t){let s;const r={};class a extends e{static _finalizeClass(){if(!this.hasOwnProperty(JSCompiler_renameProperty("generatedFrom",this)))e._finalizeClass.call(this);else{if(s)for(let o=0,d;o<s.length;o++)d=s[o],d.properties&&this.createProperties(d.properties),d.observers&&this.createObservers(d.observers,d.properties);i.properties&&this.createProperties(i.properties),i.observers&&this.createObservers(i.observers,i.properties),this._prepareTemplate()}}static get properties(){const o={};if(s)for(let d=0;d<s.length;d++)Qt(o,s[d].properties);return Qt(o,i.properties),o}static get observers(){let o=[];if(s)for(let d=0,h;d<s.length;d++)h=s[d],h.observers&&(o=o.concat(h.observers));return i.observers&&(o=o.concat(i.observers)),o}created(){super.created();const o=r.created;if(o)for(let d=0;d<o.length;d++)o[d].call(this)}_registered(){const o=a.prototype;if(!o.hasOwnProperty(JSCompiler_renameProperty("__hasRegisterFinished",o))){o.__hasRegisterFinished=!0,super._registered(),qe&&l(o);const d=Object.getPrototypeOf(this);let h=r.beforeRegister;if(h)for(let c=0;c<h.length;c++)h[c].call(d);if(h=r.registered,h)for(let c=0;c<h.length;c++)h[c].call(d)}}_applyListeners(){super._applyListeners();const o=r.listeners;if(o)for(let d=0;d<o.length;d++){const h=o[d];if(h)for(let c in h)this._addMethodEventListenerToNode(this,c,h[c])}}_ensureAttributes(){const o=r.hostAttributes;if(o)for(let d=o.length-1;d>=0;d--){const h=o[d];for(let c in h)this._ensureAttribute(c,h[c])}super._ensureAttributes()}ready(){super.ready();let o=r.ready;if(o)for(let d=0;d<o.length;d++)o[d].call(this)}attached(){super.attached();let o=r.attached;if(o)for(let d=0;d<o.length;d++)o[d].call(this)}detached(){super.detached();let o=r.detached;if(o)for(let d=0;d<o.length;d++)o[d].call(this)}attributeChanged(o,d,h){super.attributeChanged();let c=r.attributeChanged;if(c)for(let m=0;m<c.length;m++)c[m].call(this,o,d,h)}}if(t){Array.isArray(t)||(t=[t]);let n=e.prototype.behaviors;s=Bs(t,null,n),a.prototype.behaviors=n?n.concat(t):s}const l=n=>{s&&ha(n,s,r),Ps(n,i,r,Ts)};return qe||l(a.prototype),a.generatedFrom=i,a}const ua=function(i,e){i||console.warn("Polymer.Class requires `info` argument");let t=e?e(Jt):Jt;return t=ca(i,t,i.behaviors),t.is=t.prototype.is=i.is,t};/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const ce=function(i){let e;return typeof i=="function"?e=i:e=ce.Class(i),i._legacyForceObservedAttributes&&(e.prototype._legacyForceObservedAttributes=i._legacyForceObservedAttributes),customElements.define(e.is,e),e};ce.Class=ua;/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const pa={templatize(i,e){this._templatizerTemplate=i,this.ctor=nr(i,this,{mutableData:Boolean(e),parentModel:this._parentModel,instanceProps:this._instanceProps,forwardHostProp:this._forwardHostPropV2,notifyInstanceProp:this._notifyInstancePropV2})},stamp(i){return new this.ctor(i)},modelForElement(i){return ar(this._templatizerTemplate,i)}};/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const ma=ws(or(lr(HTMLElement)));class fa extends ma{static get observedAttributes(){return["mutable-data"]}constructor(){if(super(),dr)throw new Error("strictTemplatePolicy: dom-bind not allowed");this.root=null,this.$=null,this.__children=null}attributeChangedCallback(e,t,s,r){this.mutableData=!0}connectedCallback(){hr()||(this.style.display="none"),this.render()}disconnectedCallback(){this.__removeChildren()}__insertChildren(){b(b(this).parentNode).insertBefore(this.root,this)}__removeChildren(){if(this.__children)for(let e=0;e<this.__children.length;e++)this.root.appendChild(this.__children[e])}render(){let e;if(!this.__children){if(e=e||this.querySelector("template"),!e){let t=new MutationObserver(()=>{if(e=this.querySelector("template"),e)t.disconnect(),this.render();else throw new Error("dom-bind requires a <template> child")});t.observe(this,{childList:!0});return}this.root=this._stampTemplate(e),this.$=this.root.$,this.__children=[];for(let t=this.root.firstChild;t;t=t.nextSibling)this.__children[this.__children.length]=t;this._enableProperties()}this.__insertChildren(),this.dispatchEvent(new CustomEvent("dom-change",{bubbles:!0,composed:!0}))}}customElements.define("dom-bind",fa);/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/let _a=le(i=>{let e=Ki(i);class t extends e{static get properties(){return{items:{type:Array},multi:{type:Boolean,value:!1},selected:{type:Object,notify:!0},selectedItem:{type:Object,notify:!0},toggle:{type:Boolean,value:!1}}}static get observers(){return["__updateSelection(multi, items.*)"]}constructor(){super(),this.__lastItems=null,this.__lastMulti=null,this.__selectedMap=null}__updateSelection(r,a){let l=a.path;if(l==JSCompiler_renameProperty("items",this)){let n=a.base||[],o=this.__lastItems,d=this.__lastMulti;if(r!==d&&this.clearSelection(),o){let h=ht(n,o);this.__applySplices(h)}this.__lastItems=n,this.__lastMulti=r}else if(a.path==`${JSCompiler_renameProperty("items",this)}.splices`)this.__applySplices(a.value.indexSplices);else{let n=l.slice(`${JSCompiler_renameProperty("items",this)}.`.length),o=parseInt(n,10);n.indexOf(".")<0&&n==o&&this.__deselectChangedIdx(o)}}__applySplices(r){let a=this.__selectedMap;for(let n=0;n<r.length;n++){let o=r[n];a.forEach((d,h)=>{d<o.index||(d>=o.index+o.removed.length?a.set(h,d+o.addedCount-o.removed.length):a.set(h,-1))});for(let d=0;d<o.addedCount;d++){let h=o.index+d;a.has(this.items[h])&&a.set(this.items[h],h)}}this.__updateLinks();let l=0;a.forEach((n,o)=>{n<0?(this.multi?this.splice(JSCompiler_renameProperty("selected",this),l,1):this.selected=this.selectedItem=null,a.delete(o)):l++})}__updateLinks(){if(this.__dataLinkedPaths={},this.multi){let r=0;this.__selectedMap.forEach(a=>{a>=0&&this.linkPaths(`${JSCompiler_renameProperty("items",this)}.${a}`,`${JSCompiler_renameProperty("selected",this)}.${r++}`)})}else this.__selectedMap.forEach(r=>{this.linkPaths(JSCompiler_renameProperty("selected",this),`${JSCompiler_renameProperty("items",this)}.${r}`),this.linkPaths(JSCompiler_renameProperty("selectedItem",this),`${JSCompiler_renameProperty("items",this)}.${r}`)})}clearSelection(){this.__dataLinkedPaths={},this.__selectedMap=new Map,this.selected=this.multi?[]:null,this.selectedItem=null}isSelected(r){return this.__selectedMap.has(r)}isIndexSelected(r){return this.isSelected(this.items[r])}__deselectChangedIdx(r){let a=this.__selectedIndexForItemIndex(r);if(a>=0){let l=0;this.__selectedMap.forEach((n,o)=>{a==l++&&this.deselect(o)})}}__selectedIndexForItemIndex(r){let a=this.__dataLinkedPaths[`${JSCompiler_renameProperty("items",this)}.${r}`];if(a)return parseInt(a.slice(`${JSCompiler_renameProperty("selected",this)}.`.length),10)}deselect(r){let a=this.__selectedMap.get(r);if(a>=0){this.__selectedMap.delete(r);let l;this.multi&&(l=this.__selectedIndexForItemIndex(a)),this.__updateLinks(),this.multi?this.splice(JSCompiler_renameProperty("selected",this),l,1):this.selected=this.selectedItem=null}}deselectIndex(r){this.deselect(this.items[r])}select(r){this.selectIndex(this.items.indexOf(r))}selectIndex(r){let a=this.items[r];this.isSelected(a)?this.toggle&&this.deselectIndex(r):(this.multi||this.__selectedMap.clear(),this.__selectedMap.set(a,r),this.__updateLinks(),this.multi?this.push(JSCompiler_renameProperty("selected",this),a):this.selected=this.selectedItem=a)}}return t}),ga=_a(g);class Kt extends ga{static get is(){return"array-selector"}static get template(){return null}}customElements.define(Kt.is,Kt);/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const be=new F;window.ShadyCSS||(window.ShadyCSS={prepareTemplate(i,e,t){},prepareTemplateDom(i,e){},prepareTemplateStyles(i,e,t){},styleSubtree(i,e){be.processStyles(),Ke(i,e)},styleElement(i){be.processStyles()},styleDocument(i){be.processStyles(),Ke(document.body,i)},getComputedStyleValue(i,e){return fs(i,e)},flushCustomStyles(){},nativeCss:bt,nativeShadow:Le,cssBuild:ae,disableRuntime:as});window.ShadyCSS.CustomStyleInterface=be;/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Zt="include",va=window.ShadyCSS.CustomStyleInterface;class ya extends HTMLElement{constructor(){super(),this._style=null,va.addCustomStyle(this)}getStyle(){if(this._style)return this._style;const e=this.querySelector("style");if(!e)return null;this._style=e;const t=e.getAttribute(Zt);return t&&(e.removeAttribute(Zt),e.textContent=cr(t)+e.textContent),this.ownerDocument!==window.document&&window.document.head.appendChild(this),this._style}}window.customElements.define("custom-style",ya);/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/let Ms;Ms=ur._mutablePropertyChange;const ba={properties:{mutableData:Boolean},_shouldPropertyChange(i,e,t){return Ms(this,i,e,t,this.mutableData)}};/**
@license
Copyright (c) 2017 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at http://polymer.github.io/LICENSE.txt
The complete set of authors may be found at http://polymer.github.io/AUTHORS.txt
The complete set of contributors may be found at http://polymer.github.io/CONTRIBUTORS.txt
Code distributed by Google as part of the polymer project is also
subject to an additional IP rights grant found at http://polymer.github.io/PATENTS.txt
*/const Aa=ks(HTMLElement).prototype;/**
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/const Fs=f`
<custom-style>
  <style is="custom-style">
    [hidden] {
      display: none !important;
    }
  </style>
</custom-style>
<custom-style>
  <style is="custom-style">
    html {

      --layout: {
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
      };

      --layout-inline: {
        display: -ms-inline-flexbox;
        display: -webkit-inline-flex;
        display: inline-flex;
      };

      --layout-horizontal: {
        @apply --layout;

        -ms-flex-direction: row;
        -webkit-flex-direction: row;
        flex-direction: row;
      };

      --layout-horizontal-reverse: {
        @apply --layout;

        -ms-flex-direction: row-reverse;
        -webkit-flex-direction: row-reverse;
        flex-direction: row-reverse;
      };

      --layout-vertical: {
        @apply --layout;

        -ms-flex-direction: column;
        -webkit-flex-direction: column;
        flex-direction: column;
      };

      --layout-vertical-reverse: {
        @apply --layout;

        -ms-flex-direction: column-reverse;
        -webkit-flex-direction: column-reverse;
        flex-direction: column-reverse;
      };

      --layout-wrap: {
        -ms-flex-wrap: wrap;
        -webkit-flex-wrap: wrap;
        flex-wrap: wrap;
      };

      --layout-wrap-reverse: {
        -ms-flex-wrap: wrap-reverse;
        -webkit-flex-wrap: wrap-reverse;
        flex-wrap: wrap-reverse;
      };

      --layout-flex-auto: {
        -ms-flex: 1 1 auto;
        -webkit-flex: 1 1 auto;
        flex: 1 1 auto;
      };

      --layout-flex-none: {
        -ms-flex: none;
        -webkit-flex: none;
        flex: none;
      };

      --layout-flex: {
        -ms-flex: 1 1 0.000000001px;
        -webkit-flex: 1;
        flex: 1;
        -webkit-flex-basis: 0.000000001px;
        flex-basis: 0.000000001px;
      };

      --layout-flex-2: {
        -ms-flex: 2;
        -webkit-flex: 2;
        flex: 2;
      };

      --layout-flex-3: {
        -ms-flex: 3;
        -webkit-flex: 3;
        flex: 3;
      };

      --layout-flex-4: {
        -ms-flex: 4;
        -webkit-flex: 4;
        flex: 4;
      };

      --layout-flex-5: {
        -ms-flex: 5;
        -webkit-flex: 5;
        flex: 5;
      };

      --layout-flex-6: {
        -ms-flex: 6;
        -webkit-flex: 6;
        flex: 6;
      };

      --layout-flex-7: {
        -ms-flex: 7;
        -webkit-flex: 7;
        flex: 7;
      };

      --layout-flex-8: {
        -ms-flex: 8;
        -webkit-flex: 8;
        flex: 8;
      };

      --layout-flex-9: {
        -ms-flex: 9;
        -webkit-flex: 9;
        flex: 9;
      };

      --layout-flex-10: {
        -ms-flex: 10;
        -webkit-flex: 10;
        flex: 10;
      };

      --layout-flex-11: {
        -ms-flex: 11;
        -webkit-flex: 11;
        flex: 11;
      };

      --layout-flex-12: {
        -ms-flex: 12;
        -webkit-flex: 12;
        flex: 12;
      };

      /* alignment in cross axis */

      --layout-start: {
        -ms-flex-align: start;
        -webkit-align-items: flex-start;
        align-items: flex-start;
      };

      --layout-center: {
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
      };

      --layout-end: {
        -ms-flex-align: end;
        -webkit-align-items: flex-end;
        align-items: flex-end;
      };

      --layout-baseline: {
        -ms-flex-align: baseline;
        -webkit-align-items: baseline;
        align-items: baseline;
      };

      /* alignment in main axis */

      --layout-start-justified: {
        -ms-flex-pack: start;
        -webkit-justify-content: flex-start;
        justify-content: flex-start;
      };

      --layout-center-justified: {
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
      };

      --layout-end-justified: {
        -ms-flex-pack: end;
        -webkit-justify-content: flex-end;
        justify-content: flex-end;
      };

      --layout-around-justified: {
        -ms-flex-pack: distribute;
        -webkit-justify-content: space-around;
        justify-content: space-around;
      };

      --layout-justified: {
        -ms-flex-pack: justify;
        -webkit-justify-content: space-between;
        justify-content: space-between;
      };

      --layout-center-center: {
        @apply --layout-center;
        @apply --layout-center-justified;
      };

      /* self alignment */

      --layout-self-start: {
        -ms-align-self: flex-start;
        -webkit-align-self: flex-start;
        align-self: flex-start;
      };

      --layout-self-center: {
        -ms-align-self: center;
        -webkit-align-self: center;
        align-self: center;
      };

      --layout-self-end: {
        -ms-align-self: flex-end;
        -webkit-align-self: flex-end;
        align-self: flex-end;
      };

      --layout-self-stretch: {
        -ms-align-self: stretch;
        -webkit-align-self: stretch;
        align-self: stretch;
      };

      --layout-self-baseline: {
        -ms-align-self: baseline;
        -webkit-align-self: baseline;
        align-self: baseline;
      };

      /* multi-line alignment in main axis */

      --layout-start-aligned: {
        -ms-flex-line-pack: start;  /* IE10 */
        -ms-align-content: flex-start;
        -webkit-align-content: flex-start;
        align-content: flex-start;
      };

      --layout-end-aligned: {
        -ms-flex-line-pack: end;  /* IE10 */
        -ms-align-content: flex-end;
        -webkit-align-content: flex-end;
        align-content: flex-end;
      };

      --layout-center-aligned: {
        -ms-flex-line-pack: center;  /* IE10 */
        -ms-align-content: center;
        -webkit-align-content: center;
        align-content: center;
      };

      --layout-between-aligned: {
        -ms-flex-line-pack: justify;  /* IE10 */
        -ms-align-content: space-between;
        -webkit-align-content: space-between;
        align-content: space-between;
      };

      --layout-around-aligned: {
        -ms-flex-line-pack: distribute;  /* IE10 */
        -ms-align-content: space-around;
        -webkit-align-content: space-around;
        align-content: space-around;
      };

      /*******************************
                Other Layout
      *******************************/

      --layout-block: {
        display: block;
      };

      --layout-invisible: {
        visibility: hidden !important;
      };

      --layout-relative: {
        position: relative;
      };

      --layout-fit: {
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
      };

      --layout-scroll: {
        -webkit-overflow-scrolling: touch;
        overflow: auto;
      };

      --layout-fullbleed: {
        margin: 0;
        height: 100vh;
      };

      /* fixed position */

      --layout-fixed-top: {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
      };

      --layout-fixed-right: {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
      };

      --layout-fixed-bottom: {
        position: fixed;
        right: 0;
        bottom: 0;
        left: 0;
      };

      --layout-fixed-left: {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
      };

    }
  </style>
</custom-style>`;Fs.setAttribute("style","display: none;");document.head.appendChild(Fs.content);var Os=document.createElement("style");Os.textContent="[hidden] { display: none !important; }";document.head.appendChild(Os);/**
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/class S{constructor(e){S[" "](e),this.type=e&&e.type||"default",this.key=e&&e.key,e&&"value"in e&&(this.value=e.value)}get value(){var e=this.type,t=this.key;if(e&&t)return S.types[e]&&S.types[e][t]}set value(e){var t=this.type,s=this.key;t&&s&&(t=S.types[t]=S.types[t]||{},e==null?delete t[s]:t[s]=e)}get list(){var e=this.type;if(e){var t=S.types[this.type];return t?Object.keys(t).map(function(s){return xa[this.type][s]},this):[]}}byKey(e){return this.key=e,this.value}}S[" "]=function(){};S.types={};var xa=S.types;ce({is:"iron-meta",properties:{type:{type:String,value:"default"},key:{type:String},value:{type:String,notify:!0},self:{type:Boolean,observer:"_selfChanged"},__meta:{type:Boolean,computed:"__computeMeta(type, key, value)"}},hostAttributes:{hidden:!0},__computeMeta:function(i,e,t){var s=new S({type:i,key:e});return t!==void 0&&t!==s.value?s.value=t:this.value!==s.value&&(this.value=s.value),s},get list(){return this.__meta&&this.__meta.list},_selfChanged:function(i){i&&(this.value=this)},byKey:function(i){return new S({type:this.type,key:i}).value}});/**
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/ce({_template:f`
    <style>
      :host {
        @apply --layout-inline;
        @apply --layout-center-center;
        position: relative;

        vertical-align: middle;

        fill: var(--iron-icon-fill-color, currentcolor);
        stroke: var(--iron-icon-stroke-color, none);

        width: var(--iron-icon-width, 24px);
        height: var(--iron-icon-height, 24px);
        @apply --iron-icon;
      }

      :host([hidden]) {
        display: none;
      }
    </style>
`,is:"iron-icon",properties:{icon:{type:String},theme:{type:String},src:{type:String},_meta:{value:Aa.create("iron-meta",{type:"iconset"})}},observers:["_updateIcon(_meta, isAttached)","_updateIcon(theme, isAttached)","_srcChanged(src, isAttached)","_iconChanged(icon, isAttached)"],_DEFAULT_ICONSET:"icons",_iconChanged:function(i){var e=(i||"").split(":");this._iconName=e.pop(),this._iconsetName=e.pop()||this._DEFAULT_ICONSET,this._updateIcon()},_srcChanged:function(i){this._updateIcon()},_usesIconset:function(){return this.icon||!this.src},_updateIcon:function(){this._usesIconset()?(this._img&&this._img.parentNode&&w(this.root).removeChild(this._img),this._iconName===""?this._iconset&&this._iconset.removeIcon(this):this._iconsetName&&this._meta&&(this._iconset=this._meta.byKey(this._iconsetName),this._iconset?(this._iconset.applyIcon(this,this._iconName,this.theme),this.unlisten(window,"iron-iconset-added","_updateIcon")):this.listen(window,"iron-iconset-added","_updateIcon"))):(this._iconset&&this._iconset.removeIcon(this),this._img||(this._img=document.createElement("img"),this._img.style.width="100%",this._img.style.height="100%",this._img.draggable=!1),this._img.src=this.src,w(this.root).appendChild(this._img))}});/**
@license
Copyright (c) 2015 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/var ge=new Set;const wa={properties:{_parentResizable:{type:Object,observer:"_parentResizableChanged"},_notifyingDescendant:{type:Boolean,value:!1}},listeners:{"iron-request-resize-notifications":"_onIronRequestResizeNotifications"},created:function(){this._interestedResizables=[],this._boundNotifyResize=this.notifyResize.bind(this),this._boundOnDescendantIronResize=this._onDescendantIronResize.bind(this)},attached:function(){this._requestResizeNotifications()},detached:function(){this._parentResizable?this._parentResizable.stopResizeNotificationsFor(this):(ge.delete(this),window.removeEventListener("resize",this._boundNotifyResize)),this._parentResizable=null},notifyResize:function(){!this.isAttached||(this._interestedResizables.forEach(function(i){this.resizerShouldNotify(i)&&this._notifyDescendant(i)},this),this._fireResize())},assignParentResizable:function(i){this._parentResizable&&this._parentResizable.stopResizeNotificationsFor(this),this._parentResizable=i,i&&i._interestedResizables.indexOf(this)===-1&&(i._interestedResizables.push(this),i._subscribeIronResize(this))},stopResizeNotificationsFor:function(i){var e=this._interestedResizables.indexOf(i);e>-1&&(this._interestedResizables.splice(e,1),this._unsubscribeIronResize(i))},_subscribeIronResize:function(i){i.addEventListener("iron-resize",this._boundOnDescendantIronResize)},_unsubscribeIronResize:function(i){i.removeEventListener("iron-resize",this._boundOnDescendantIronResize)},resizerShouldNotify:function(i){return!0},_onDescendantIronResize:function(i){if(this._notifyingDescendant){i.stopPropagation();return}pr||this._fireResize()},_fireResize:function(){this.fire("iron-resize",null,{node:this,bubbles:!1})},_onIronRequestResizeNotifications:function(i){var e=w(i).rootTarget;e!==this&&(e.assignParentResizable(this),this._notifyDescendant(e),i.stopPropagation())},_parentResizableChanged:function(i){i&&window.removeEventListener("resize",this._boundNotifyResize)},_notifyDescendant:function(i){!this.isAttached||(this._notifyingDescendant=!0,i.notifyResize(),this._notifyingDescendant=!1)},_requestResizeNotifications:function(){if(!!this.isAttached)if(document.readyState==="loading"){var i=this._requestResizeNotifications.bind(this);document.addEventListener("readystatechange",function e(){document.removeEventListener("readystatechange",e),i()})}else this._findParent(),this._parentResizable?this._parentResizable._interestedResizables.forEach(function(e){e!==this&&e._findParent()},this):(ge.forEach(function(e){e!==this&&e._findParent()},this),window.addEventListener("resize",this._boundNotifyResize),this.notifyResize())},_findParent:function(){this.assignParentResizable(null),this.fire("iron-request-resize-notifications",null,{node:this,bubbles:!0,cancelable:!0}),this._parentResizable?ge.delete(this):ge.add(this)}};/**
@license
Copyright (c) 2016 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/const Ca={properties:{scrollTarget:{type:HTMLElement,value:function(){return this._defaultScrollTarget}}},observers:["_scrollTargetChanged(scrollTarget, isAttached)"],_shouldHaveListener:!0,_scrollTargetChanged:function(i,e){if(this._oldScrollTarget&&(this._toggleScrollListener(!1,this._oldScrollTarget),this._oldScrollTarget=null),!!e)if(i==="document")this.scrollTarget=this._doc;else if(typeof i=="string"){var t=this.domHost;this.scrollTarget=t&&t.$?t.$[i]:w(this.ownerDocument).querySelector("#"+i)}else this._isValidScrollTarget()&&(this._oldScrollTarget=i,this._toggleScrollListener(this._shouldHaveListener,i))},_scrollHandler:function(){},get _defaultScrollTarget(){return this._doc},get _doc(){return this.ownerDocument.documentElement},get _scrollTop(){return this._isValidScrollTarget()?this.scrollTarget===this._doc?window.pageYOffset:this.scrollTarget.scrollTop:0},get _scrollLeft(){return this._isValidScrollTarget()?this.scrollTarget===this._doc?window.pageXOffset:this.scrollTarget.scrollLeft:0},set _scrollTop(i){this.scrollTarget===this._doc?window.scrollTo(window.pageXOffset,i):this._isValidScrollTarget()&&(this.scrollTarget.scrollTop=i)},set _scrollLeft(i){this.scrollTarget===this._doc?window.scrollTo(i,window.pageYOffset):this._isValidScrollTarget()&&(this.scrollTarget.scrollLeft=i)},scroll:function(i,e){var t;typeof i=="object"?(t=i.left,e=i.top):t=i,t=t||0,e=e||0,this.scrollTarget===this._doc?window.scrollTo(t,e):this._isValidScrollTarget()&&(this.scrollTarget.scrollLeft=t,this.scrollTarget.scrollTop=e)},get _scrollTargetWidth(){return this._isValidScrollTarget()?this.scrollTarget===this._doc?window.innerWidth:this.scrollTarget.offsetWidth:0},get _scrollTargetHeight(){return this._isValidScrollTarget()?this.scrollTarget===this._doc?window.innerHeight:this.scrollTarget.offsetHeight:0},_isValidScrollTarget:function(){return this.scrollTarget instanceof HTMLElement},_toggleScrollListener:function(i,e){var t=e===this._doc?window:e;i?this._boundScrollHandler||(this._boundScrollHandler=this._scrollHandler.bind(this),t.addEventListener("scroll",this._boundScrollHandler)):this._boundScrollHandler&&(t.removeEventListener("scroll",this._boundScrollHandler),this._boundScrollHandler=null)},toggleScrollListener:function(i){this._shouldHaveListener=i,this._toggleScrollListener(i,this.scrollTarget)}};/**
@license
Copyright (c) 2016 The Polymer Project Authors. All rights reserved.
This code may only be used under the BSD style license found at
http://polymer.github.io/LICENSE.txt The complete set of authors may be found at
http://polymer.github.io/AUTHORS.txt The complete set of contributors may be
found at http://polymer.github.io/CONTRIBUTORS.txt Code distributed by Google as
part of the polymer project is also subject to an additional IP rights grant
found at http://polymer.github.io/PATENTS.txt
*/var Xt=navigator.userAgent.match(/iP(?:hone|ad;(?: U;)? CPU) OS (\d+)/),Ea=Xt&&Xt[1]>=8,ei=3,ti="-10000px",X=-100;ce({_template:f`
    <style>
      :host {
        display: block;
      }

      @media only screen and (-webkit-max-device-pixel-ratio: 1) {
        :host {
          will-change: transform;
        }
      }

      #items {
        @apply --iron-list-items-container;
        position: relative;
      }

      :host(:not([grid])) #items > ::slotted(*) {
        width: 100%;
      }

      #items > ::slotted(*) {
        box-sizing: border-box;
        margin: 0;
        position: absolute;
        top: 0;
        will-change: transform;
      }
    </style>

    <array-selector id="selector" items="{{items}}" selected="{{selectedItems}}" selected-item="{{selectedItem}}"></array-selector>

    <div id="items">
      <slot></slot>
    </div>
`,is:"iron-list",properties:{items:{type:Array},as:{type:String,value:"item"},indexAs:{type:String,value:"index"},selectedAs:{type:String,value:"selected"},grid:{type:Boolean,value:!1,reflectToAttribute:!0,observer:"_gridChanged"},selectionEnabled:{type:Boolean,value:!1},selectedItem:{type:Object,notify:!0},selectedItems:{type:Object,notify:!0},multiSelection:{type:Boolean,value:!1},scrollOffset:{type:Number,value:0}},observers:["_itemsChanged(items.*)","_selectionEnabledChanged(selectionEnabled)","_multiSelectionChanged(multiSelection)","_setOverflow(scrollTarget, scrollOffset)"],behaviors:[pa,wa,Ca,ba],_ratio:.5,_scrollerPaddingTop:0,_scrollPosition:0,_physicalSize:0,_physicalAverage:0,_physicalAverageCount:0,_physicalTop:0,_virtualCount:0,_estScrollHeight:0,_scrollHeight:0,_viewportHeight:0,_viewportWidth:0,_physicalItems:null,_physicalSizes:null,_firstVisibleIndexVal:null,_lastVisibleIndexVal:null,_maxPages:2,_focusedItem:null,_focusedVirtualIndex:-1,_focusedPhysicalIndex:-1,_offscreenFocusedItem:null,_focusBackfillItem:null,_itemsPerRow:1,_itemWidth:0,_rowHeight:0,_templateCost:0,_parentModel:!0,get _physicalBottom(){return this._physicalTop+this._physicalSize},get _scrollBottom(){return this._scrollPosition+this._viewportHeight},get _virtualEnd(){return this._virtualStart+this._physicalCount-1},get _hiddenContentSize(){var i=this.grid?this._physicalRows*this._rowHeight:this._physicalSize;return i-this._viewportHeight},get _itemsParent(){return w(w(this._userTemplate).parentNode)},get _maxScrollTop(){return this._estScrollHeight-this._viewportHeight+this._scrollOffset},get _maxVirtualStart(){var i=this._convertIndexToCompleteRow(this._virtualCount);return Math.max(0,i-this._physicalCount)},set _virtualStart(i){i=this._clamp(i,0,this._maxVirtualStart),this.grid&&(i=i-i%this._itemsPerRow),this._virtualStartVal=i},get _virtualStart(){return this._virtualStartVal||0},set _physicalStart(i){i=i%this._physicalCount,i<0&&(i=this._physicalCount+i),this.grid&&(i=i-i%this._itemsPerRow),this._physicalStartVal=i},get _physicalStart(){return this._physicalStartVal||0},get _physicalEnd(){return(this._physicalStart+this._physicalCount-1)%this._physicalCount},set _physicalCount(i){this._physicalCountVal=i},get _physicalCount(){return this._physicalCountVal||0},get _optPhysicalSize(){return this._viewportHeight===0?1/0:this._viewportHeight*this._maxPages},get _isVisible(){return Boolean(this.offsetWidth||this.offsetHeight)},get firstVisibleIndex(){var i=this._firstVisibleIndexVal;if(i==null){var e=this._physicalTop+this._scrollOffset;i=this._iterateItems(function(t,s){if(e+=this._getPhysicalSizeIncrement(t),e>this._scrollPosition)return this.grid?s-s%this._itemsPerRow:s;if(this.grid&&this._virtualCount-1===s)return s-s%this._itemsPerRow})||0,this._firstVisibleIndexVal=i}return i},get lastVisibleIndex(){var i=this._lastVisibleIndexVal;if(i==null){if(this.grid)i=Math.min(this._virtualCount,this.firstVisibleIndex+this._estRowsInView*this._itemsPerRow-1);else{var e=this._physicalTop+this._scrollOffset;this._iterateItems(function(t,s){e<this._scrollBottom&&(i=s),e+=this._getPhysicalSizeIncrement(t)})}this._lastVisibleIndexVal=i}return i},get _defaultScrollTarget(){return this},get _virtualRowCount(){return Math.ceil(this._virtualCount/this._itemsPerRow)},get _estRowsInView(){return Math.ceil(this._viewportHeight/this._rowHeight)},get _physicalRows(){return Math.ceil(this._physicalCount/this._itemsPerRow)},get _scrollOffset(){return this._scrollerPaddingTop+this.scrollOffset},ready:function(){this.addEventListener("focus",this._didFocus.bind(this),!0)},attached:function(){this._debounce("_render",this._render,K),this.listen(this,"iron-resize","_resizeHandler"),this.listen(this,"keydown","_keydownHandler")},detached:function(){this.unlisten(this,"iron-resize","_resizeHandler"),this.unlisten(this,"keydown","_keydownHandler")},_setOverflow:function(i){this.style.webkitOverflowScrolling=i===this?"touch":"",this.style.overflowY=i===this?"auto":"",this._lastVisibleIndexVal=null,this._firstVisibleIndexVal=null,this._debounce("_render",this._render,K)},updateViewportBoundaries:function(){var i=window.getComputedStyle(this);this._scrollerPaddingTop=this.scrollTarget===this?0:parseInt(i["padding-top"],10),this._isRTL=Boolean(i.direction==="rtl"),this._viewportWidth=this.$.items.offsetWidth,this._viewportHeight=this._scrollTargetHeight,this.grid&&this._updateGridMetrics()},_scrollHandler:function(){var i=Math.max(0,Math.min(this._maxScrollTop,this._scrollTop)),e=i-this._scrollPosition,t=e>=0;if(this._scrollPosition=i,this._firstVisibleIndexVal=null,this._lastVisibleIndexVal=null,Math.abs(e)>this._physicalSize&&this._physicalSize>0){e=e-this._scrollOffset;var s=Math.round(e/this._physicalAverage)*this._itemsPerRow;this._virtualStart=this._virtualStart+s,this._physicalStart=this._physicalStart+s,this._physicalTop=Math.min(Math.floor(this._virtualStart/this._itemsPerRow)*this._physicalAverage,this._scrollPosition),this._update()}else if(this._physicalCount>0){var r=this._getReusables(t);t?(this._physicalTop=r.physicalTop,this._virtualStart=this._virtualStart+r.indexes.length,this._physicalStart=this._physicalStart+r.indexes.length):(this._virtualStart=this._virtualStart-r.indexes.length,this._physicalStart=this._physicalStart-r.indexes.length),this._update(r.indexes,t?null:r.indexes),this._debounce("_increasePoolIfNeeded",this._increasePoolIfNeeded.bind(this,0),H)}},_getReusables:function(i){var e,t,s,r=[],a=this._hiddenContentSize*this._ratio,l=this._virtualStart,n=this._virtualEnd,o=this._physicalCount,d=this._physicalTop+this._scrollOffset,h=this._physicalBottom+this._scrollOffset,c=this._scrollPosition,m=this._scrollBottom;for(i?(e=this._physicalStart,this._physicalEnd,t=c-d):(e=this._physicalEnd,this._physicalStart,t=h-m);s=this._getPhysicalSizeIncrement(e),t=t-s,!(r.length>=o||t<=a);)if(i){if(n+r.length+1>=this._virtualCount||d+s>=c-this._scrollOffset)break;r.push(e),d=d+s,e=(e+1)%o}else{if(l-r.length<=0||d+this._physicalSize-s<=m)break;r.push(e),d=d-s,e=e===0?o-1:e-1}return{indexes:r,physicalTop:d-this._scrollOffset}},_update:function(i,e){if(!(i&&i.length===0||this._physicalCount===0)){if(this._manageFocus(),this._assignModels(i),this._updateMetrics(i),e)for(;e.length;){var t=e.pop();this._physicalTop-=this._getPhysicalSizeIncrement(t)}this._positionItems(),this._updateScrollerSize()}},_createPool:function(i){this._ensureTemplatized();var e,t,s=new Array(i);for(e=0;e<i;e++)t=this.stamp(null),s[e]=t.root.querySelector("*"),this._itemsParent.appendChild(t.root);return s},_isClientFull:function(){return this._scrollBottom!=0&&this._physicalBottom-1>=this._scrollBottom&&this._physicalTop<=this._scrollPosition},_increasePoolIfNeeded:function(i){var e=this._clamp(this._physicalCount+i,ei,this._virtualCount-this._virtualStart);if(e=this._convertIndexToCompleteRow(e),this.grid){var t=e%this._itemsPerRow;t&&e-t<=this._physicalCount&&(e+=this._itemsPerRow),e-=t}var s=e-this._physicalCount,r=Math.round(this._physicalCount*.5);if(!(s<0)){if(s>0){var a=window.performance.now();[].push.apply(this._physicalItems,this._createPool(s));for(var l=0;l<s;l++)this._physicalSizes.push(0);this._physicalCount=this._physicalCount+s,this._physicalStart>this._physicalEnd&&this._isIndexRendered(this._focusedVirtualIndex)&&this._getPhysicalIndex(this._focusedVirtualIndex)<this._physicalEnd&&(this._physicalStart=this._physicalStart+s),this._update(),this._templateCost=(window.performance.now()-a)/s,r=Math.round(this._physicalCount*.5)}this._virtualEnd>=this._virtualCount-1||r===0||(this._isClientFull()?this._physicalSize<this._optPhysicalSize&&this._debounce("_increasePoolIfNeeded",this._increasePoolIfNeeded.bind(this,this._clamp(Math.round(50/this._templateCost),1,r)),mr):this._debounce("_increasePoolIfNeeded",this._increasePoolIfNeeded.bind(this,r),H))}},_render:function(){if(!(!this.isAttached||!this._isVisible))if(this._physicalCount!==0){var i=this._getReusables(!0);this._physicalTop=i.physicalTop,this._virtualStart=this._virtualStart+i.indexes.length,this._physicalStart=this._physicalStart+i.indexes.length,this._update(i.indexes),this._update(),this._increasePoolIfNeeded(0)}else this._virtualCount>0&&(this.updateViewportBoundaries(),this._increasePoolIfNeeded(ei))},_ensureTemplatized:function(){if(!this.ctor){this._userTemplate=this.queryEffectiveChildren("template"),this._userTemplate||console.warn("iron-list requires a template to be provided in light-dom");var i={};i.__key__=!0,i[this.as]=!0,i[this.indexAs]=!0,i[this.selectedAs]=!0,i.tabIndex=!0,this._instanceProps=i,this.templatize(this._userTemplate,this.mutableData)}},_gridChanged:function(i,e){typeof e>"u"||(this.notifyResize(),Ne(),i&&this._updateGridMetrics())},_itemsChanged:function(i){if(i.path==="items")this._virtualStart=0,this._physicalTop=0,this._virtualCount=this.items?this.items.length:0,this._physicalIndexForKey={},this._firstVisibleIndexVal=null,this._lastVisibleIndexVal=null,this._physicalCount=this._physicalCount||0,this._physicalItems=this._physicalItems||[],this._physicalSizes=this._physicalSizes||[],this._physicalStart=0,this._scrollTop>this._scrollOffset&&this._resetScrollPosition(0),this._removeFocusedItem(),this._debounce("_render",this._render,K);else if(i.path==="items.splices"){this._adjustVirtualIndex(i.value.indexSplices),this._virtualCount=this.items?this.items.length:0;var e=i.value.indexSplices.some(function(r){return r.addedCount>0||r.removed.length>0});if(e){var t=this._getActiveElement();this.contains(t)&&t.blur()}var s=i.value.indexSplices.some(function(r){return r.index+r.addedCount>=this._virtualStart&&r.index<=this._virtualEnd},this);(!this._isClientFull()||s)&&this._debounce("_render",this._render,K)}else i.path!=="items.length"&&this._forwardItemPath(i.path,i.value)},_forwardItemPath:function(i,e){i=i.slice(6);var t=i.indexOf(".");t===-1&&(t=i.length);var s,r,a,l=this.modelForElement(this._offscreenFocusedItem),n=parseInt(i.substring(0,t),10);s=this._isIndexRendered(n),s?(r=this._getPhysicalIndex(n),a=this.modelForElement(this._physicalItems[r])):l&&(a=l),!(!a||a[this.indexAs]!==n)&&(i=i.substring(t+1),i=this.as+(i?"."+i:""),a._setPendingPropertyOrPath(i,e,!1,!0),a._flushProperties&&a._flushProperties(),s&&(this._updateMetrics([r]),this._positionItems(),this._updateScrollerSize()))},_adjustVirtualIndex:function(i){i.forEach(function(e){if(e.removed.forEach(this._removeItem,this),e.index<this._virtualStart){var t=Math.max(e.addedCount-e.removed.length,e.index-this._virtualStart);this._virtualStart=this._virtualStart+t,this._focusedVirtualIndex>=0&&(this._focusedVirtualIndex=this._focusedVirtualIndex+t)}},this)},_removeItem:function(i){this.$.selector.deselect(i),this._focusedItem&&this.modelForElement(this._focusedItem)[this.as]===i&&this._removeFocusedItem()},_iterateItems:function(i,e){var t,s,r,a;if(arguments.length===2&&e){for(a=0;a<e.length;a++)if(t=e[a],s=this._computeVidx(t),(r=i.call(this,t,s))!=null)return r}else{for(t=this._physicalStart,s=this._virtualStart;t<this._physicalCount;t++,s++)if((r=i.call(this,t,s))!=null)return r;for(t=0;t<this._physicalStart;t++,s++)if((r=i.call(this,t,s))!=null)return r}},_computeVidx:function(i){return i>=this._physicalStart?this._virtualStart+(i-this._physicalStart):this._virtualStart+(this._physicalCount-this._physicalStart)+i},_assignModels:function(i){this._iterateItems(function(e,t){var s=this._physicalItems[e],r=this.items&&this.items[t];if(r!=null){var a=this.modelForElement(s);a.__key__=null,this._forwardProperty(a,this.as,r),this._forwardProperty(a,this.selectedAs,this.$.selector.isSelected(r)),this._forwardProperty(a,this.indexAs,t),this._forwardProperty(a,"tabIndex",this._focusedVirtualIndex===t?0:-1),this._physicalIndexForKey[a.__key__]=e,a._flushProperties&&a._flushProperties(!0),s.removeAttribute("hidden")}else s.setAttribute("hidden","")},i)},_updateMetrics:function(i){Ne();var e=0,t=0,s=this._physicalAverageCount,r=this._physicalAverage;this._iterateItems(function(a,l){t+=this._physicalSizes[a],this._physicalSizes[a]=this._physicalItems[a].offsetHeight,e+=this._physicalSizes[a],this._physicalAverageCount+=this._physicalSizes[a]?1:0},i),this.grid?(this._updateGridMetrics(),this._physicalSize=Math.ceil(this._physicalCount/this._itemsPerRow)*this._rowHeight):(t=this._itemsPerRow===1?t:Math.ceil(this._physicalCount/this._itemsPerRow)*this._rowHeight,this._physicalSize=this._physicalSize+e-t,this._itemsPerRow=1),this._physicalAverageCount!==s&&(this._physicalAverage=Math.round((r*s+e)/this._physicalAverageCount))},_updateGridMetrics:function(){this._itemWidth=this._physicalCount>0?this._physicalItems[0].getBoundingClientRect().width:200,this._rowHeight=this._physicalCount>0?this._physicalItems[0].offsetHeight:200,this._itemsPerRow=this._itemWidth?Math.floor(this._viewportWidth/this._itemWidth):this._itemsPerRow},_positionItems:function(){this._adjustScrollPosition();var i=this._physicalTop;if(this.grid){var e=this._itemsPerRow*this._itemWidth,t=(this._viewportWidth-e)/2;this._iterateItems(function(s,r){var a=r%this._itemsPerRow,l=Math.floor(a*this._itemWidth+t);this._isRTL&&(l=l*-1),this.translate3d(l+"px",i+"px",0,this._physicalItems[s]),this._shouldRenderNextRow(r)&&(i+=this._rowHeight)})}else{const s=[];this._iterateItems(function(r,a){const l=this._physicalItems[r];this.translate3d(0,i+"px",0,l),i+=this._physicalSizes[r];const n=l.id;n&&s.push(n)}),s.length&&this.setAttribute("aria-owns",s.join(" "))}},_getPhysicalSizeIncrement:function(i){return this.grid?this._computeVidx(i)%this._itemsPerRow!==this._itemsPerRow-1?0:this._rowHeight:this._physicalSizes[i]},_shouldRenderNextRow:function(i){return i%this._itemsPerRow===this._itemsPerRow-1},_adjustScrollPosition:function(){var i=this._virtualStart===0?this._physicalTop:Math.min(this._scrollPosition+this._physicalTop,0);if(i!==0){this._physicalTop=this._physicalTop-i;var e=this._scrollPosition;!Ea&&e>0&&this._resetScrollPosition(e-i)}},_resetScrollPosition:function(i){this.scrollTarget&&i>=0&&(this._scrollTop=i,this._scrollPosition=this._scrollTop)},_updateScrollerSize:function(i){this.grid?this._estScrollHeight=this._virtualRowCount*this._rowHeight:this._estScrollHeight=this._physicalBottom+Math.max(this._virtualCount-this._physicalCount-this._virtualStart,0)*this._physicalAverage,i=i||this._scrollHeight===0,i=i||this._scrollPosition>=this._estScrollHeight-this._physicalSize,i=i||this.grid&&this.$.items.style.height<this._estScrollHeight,(i||Math.abs(this._estScrollHeight-this._scrollHeight)>=this._viewportHeight)&&(this.$.items.style.height=this._estScrollHeight+"px",this._scrollHeight=this._estScrollHeight)},scrollToItem:function(i){return this.scrollToIndex(this.items.indexOf(i))},scrollToIndex:function(i){if(!(typeof i!="number"||i<0||i>this.items.length-1)&&(Ne(),this._physicalCount!==0)){i=this._clamp(i,0,this._virtualCount-1),(!this._isIndexRendered(i)||i>=this._maxVirtualStart)&&(this._virtualStart=this.grid?i-this._itemsPerRow*2:i-1),this._manageFocus(),this._assignModels(),this._updateMetrics(),this._physicalTop=Math.floor(this._virtualStart/this._itemsPerRow)*this._physicalAverage;for(var e=this._physicalStart,t=this._virtualStart,s=0,r=this._hiddenContentSize;t<i&&s<=r;)s=s+this._getPhysicalSizeIncrement(e),e=(e+1)%this._physicalCount,t++;this._updateScrollerSize(!0),this._positionItems(),this._resetScrollPosition(this._physicalTop+this._scrollOffset+s),this._increasePoolIfNeeded(0),this._firstVisibleIndexVal=null,this._lastVisibleIndexVal=null}},_resetAverage:function(){this._physicalAverage=0,this._physicalAverageCount=0},_resizeHandler:function(){this._debounce("_render",function(){this._firstVisibleIndexVal=null,this._lastVisibleIndexVal=null,this._isVisible?(this.updateViewportBoundaries(),this.toggleScrollListener(!0),this._resetAverage(),this._render()):this.toggleScrollListener(!1)},K)},selectItem:function(i){return this.selectIndex(this.items.indexOf(i))},selectIndex:function(i){if(!(i<0||i>=this._virtualCount)){if(!this.multiSelection&&this.selectedItem&&this.clearSelection(),this._isIndexRendered(i)){var e=this.modelForElement(this._physicalItems[this._getPhysicalIndex(i)]);e&&(e[this.selectedAs]=!0),this.updateSizeForIndex(i)}this.$.selector.selectIndex(i)}},deselectItem:function(i){return this.deselectIndex(this.items.indexOf(i))},deselectIndex:function(i){if(!(i<0||i>=this._virtualCount)){if(this._isIndexRendered(i)){var e=this.modelForElement(this._physicalItems[this._getPhysicalIndex(i)]);e[this.selectedAs]=!1,this.updateSizeForIndex(i)}this.$.selector.deselectIndex(i)}},toggleSelectionForItem:function(i){return this.toggleSelectionForIndex(this.items.indexOf(i))},toggleSelectionForIndex:function(i){var e=this.$.selector.isIndexSelected?this.$.selector.isIndexSelected(i):this.$.selector.isSelected(this.items[i]);e?this.deselectIndex(i):this.selectIndex(i)},clearSelection:function(){this._iterateItems(function(i,e){this.modelForElement(this._physicalItems[i])[this.selectedAs]=!1}),this.$.selector.clearSelection()},_selectionEnabledChanged:function(i){var e=i?this.listen:this.unlisten;e.call(this,this,"tap","_selectionHandler")},_selectionHandler:function(i){var e=this.modelForElement(i.target);if(!!e){var t,s,r=w(i).path[0],a=this._getActiveElement(),l=this._physicalItems[this._getPhysicalIndex(e[this.indexAs])];r.localName==="input"||r.localName==="button"||r.localName==="select"||(t=e.tabIndex,e.tabIndex=X,s=a?a.tabIndex:-1,e.tabIndex=t,!(a&&l!==a&&l.contains(a)&&s!==X)&&this.toggleSelectionForItem(e[this.as]))}},_multiSelectionChanged:function(i){this.clearSelection(),this.$.selector.multi=i},updateSizeForItem:function(i){return this.updateSizeForIndex(this.items.indexOf(i))},updateSizeForIndex:function(i){return this._isIndexRendered(i)&&(this._updateMetrics([this._getPhysicalIndex(i)]),this._positionItems()),null},_manageFocus:function(){var i=this._focusedVirtualIndex;i>=0&&i<this._virtualCount?this._isIndexRendered(i)?this._restoreFocusedItem():this._createFocusBackfillItem():this._virtualCount>0&&this._physicalCount>0&&(this._focusedPhysicalIndex=this._physicalStart,this._focusedVirtualIndex=this._virtualStart,this._focusedItem=this._physicalItems[this._physicalStart])},_convertIndexToCompleteRow:function(i){return this._itemsPerRow=this._itemsPerRow||1,this.grid?Math.ceil(i/this._itemsPerRow)*this._itemsPerRow:i},_isIndexRendered:function(i){return i>=this._virtualStart&&i<=this._virtualEnd},_isIndexVisible:function(i){return i>=this.firstVisibleIndex&&i<=this.lastVisibleIndex},_getPhysicalIndex:function(i){return(this._physicalStart+(i-this._virtualStart))%this._physicalCount},focusItem:function(i){this._focusPhysicalItem(i)},_focusPhysicalItem:function(i){if(!(i<0||i>=this._virtualCount)){this._restoreFocusedItem(),this._isIndexRendered(i)||this.scrollToIndex(i);var e=this._physicalItems[this._getPhysicalIndex(i)],t=this.modelForElement(e),s;t.tabIndex=X,e.tabIndex===X&&(s=e),s||(s=w(e).querySelector('[tabindex="'+X+'"]')),t.tabIndex=0,this._focusedVirtualIndex=i,s&&s.focus()}},_removeFocusedItem:function(){this._offscreenFocusedItem&&this._itemsParent.removeChild(this._offscreenFocusedItem),this._offscreenFocusedItem=null,this._focusBackfillItem=null,this._focusedItem=null,this._focusedVirtualIndex=-1,this._focusedPhysicalIndex=-1},_createFocusBackfillItem:function(){var i=this._focusedPhysicalIndex;if(!(this._offscreenFocusedItem||this._focusedVirtualIndex<0)){if(!this._focusBackfillItem){var e=this.stamp(null);this._focusBackfillItem=e.root.querySelector("*"),this._itemsParent.appendChild(e.root)}this._offscreenFocusedItem=this._physicalItems[i],this.modelForElement(this._offscreenFocusedItem).tabIndex=0,this._physicalItems[i]=this._focusBackfillItem,this._focusedPhysicalIndex=i,this.translate3d(0,ti,0,this._offscreenFocusedItem)}},_restoreFocusedItem:function(){if(!(!this._offscreenFocusedItem||this._focusedVirtualIndex<0)){this._assignModels();var i=this._focusedPhysicalIndex=this._getPhysicalIndex(this._focusedVirtualIndex),e=this._physicalItems[i];if(!!e){var t=this.modelForElement(e),s=this.modelForElement(this._offscreenFocusedItem);t[this.as]===s[this.as]?(this._focusBackfillItem=e,t.tabIndex=-1,this._physicalItems[i]=this._offscreenFocusedItem,this.translate3d(0,ti,0,this._focusBackfillItem)):(this._removeFocusedItem(),this._focusBackfillItem=null),this._offscreenFocusedItem=null}}},_didFocus:function(i){var e=this.modelForElement(i.target),t=this.modelForElement(this._focusedItem),s=this._offscreenFocusedItem!==null,r=this._focusedVirtualIndex;!e||(t===e?this._isIndexVisible(r)||this.scrollToIndex(r):(this._restoreFocusedItem(),t&&(t.tabIndex=-1),e.tabIndex=0,r=e[this.indexAs],this._focusedVirtualIndex=r,this._focusedPhysicalIndex=this._getPhysicalIndex(r),this._focusedItem=this._physicalItems[this._focusedPhysicalIndex],s&&!this._offscreenFocusedItem&&this._update()))},_keydownHandler:function(i){switch(i.keyCode){case 40:this._focusedVirtualIndex<this._virtualCount-1&&i.preventDefault(),this._focusPhysicalItem(this._focusedVirtualIndex+(this.grid?this._itemsPerRow:1));break;case 39:this.grid&&this._focusPhysicalItem(this._focusedVirtualIndex+(this._isRTL?-1:1));break;case 38:this._focusedVirtualIndex>0&&i.preventDefault(),this._focusPhysicalItem(this._focusedVirtualIndex-(this.grid?this._itemsPerRow:1));break;case 37:this.grid&&this._focusPhysicalItem(this._focusedVirtualIndex+(this._isRTL?1:-1));break;case 13:this._focusPhysicalItem(this._focusedVirtualIndex),this.selectionEnabled&&this._selectionHandler(i);break}},_clamp:function(i,e,t){return Math.min(t,Math.max(e,i))},_debounce:function(i,e,t){this._debouncers=this._debouncers||{},this._debouncers[i]=oe.debounce(this._debouncers[i],t,e.bind(this)),fr(this._debouncers[i])},_forwardProperty:function(i,e,t){i._setPendingProperty(e,t)},_forwardHostPropV2:function(i,e){(this._physicalItems||[]).concat([this._offscreenFocusedItem,this._focusBackfillItem]).forEach(function(t){t&&this.modelForElement(t).forwardHostProp(i,e)},this)},_notifyInstancePropV2:function(i,e,t){if(_r(this.as,e)){var s=i[this.indexAs];e==this.as&&(this.items[s]=t),this.notifyPath(gr(this.as,"items."+s,e),t)}},_getStampedChildren:function(){return this._physicalItems},_forwardInstancePath:function(i,e,t){e.indexOf(this.as+".")===0&&this.notifyPath("items."+i.__key__+"."+e.slice(this.as.length+1),t)},_forwardParentPath:function(i,e){(this._physicalItems||[]).concat([this._offscreenFocusedItem,this._focusBackfillItem]).forEach(function(t){t&&this.modelForElement(t).notifyPath(i,e)},this)},_forwardParentProp:function(i,e){(this._physicalItems||[]).concat([this._offscreenFocusedItem,this._focusBackfillItem]).forEach(function(t){t&&(this.modelForElement(t)[i]=e)},this)},_getActiveElement:function(){var i=this._itemsParent.node.domHost;return w(i?i.root:document).activeElement}});const Rs=p`
  :host {
    margin: var(--lumo-space-xs) 0;
    outline: none;
  }

  [part='summary'] {
    display: flex;
    align-items: center;
    width: 100%;
    outline: none;
    padding: var(--lumo-space-s) 0;
    box-sizing: border-box;
    font-family: var(--lumo-font-family);
    font-size: var(--lumo-font-size-m);
    font-weight: 500;
    line-height: var(--lumo-line-height-xs);
    color: var(--lumo-secondary-text-color);
    background-color: inherit;
    border-radius: var(--lumo-border-radius-m);
    cursor: var(--lumo-clickable-cursor);
    -webkit-tap-highlight-color: transparent;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }

  :host([focus-ring]) [part='summary'] {
    box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct);
  }

  [part='toggle'] {
    display: block;
    width: 1em;
    height: 1em;
    margin-left: calc(var(--lumo-space-xs) * -1);
    margin-right: var(--lumo-space-xs);
    font-size: var(--lumo-icon-size-s);
    line-height: 1;
    color: var(--lumo-contrast-60pct);
    font-family: 'lumo-icons';
    cursor: var(--lumo-clickable-cursor);
  }

  :host([disabled]) [part='summary'],
  :host([disabled]) [part='toggle'] {
    color: var(--lumo-disabled-text-color);
    cursor: default;
  }

  @media (hover: hover) {
    :host(:not([disabled])) [part='summary']:hover,
    :host(:not([disabled])) [part='summary']:hover [part='toggle'] {
      color: var(--lumo-contrast-80pct);
    }
  }

  [part='toggle']::before {
    content: var(--lumo-icons-angle-right);
  }

  :host([opened]) [part='toggle'] {
    transform: rotate(90deg);
  }

  [part='content'] {
    padding: var(--lumo-space-xs) 0 var(--lumo-space-s);
    font-size: var(--lumo-font-size-m);
    line-height: var(--lumo-line-height-m);
  }

  :host([theme~='filled']) {
    background-color: var(--lumo-contrast-5pct);
    border-radius: var(--lumo-border-radius-m);
  }

  :host([theme~='filled']) [part='summary'] {
    padding: var(--lumo-space-s) calc(var(--lumo-space-s) + var(--lumo-space-xs) / 2);
  }

  :host([theme~='filled']) [part='content'] {
    padding-left: var(--lumo-space-m);
    padding-right: var(--lumo-space-m);
  }

  :host([theme~='small']) [part='summary'] {
    padding-top: var(--lumo-space-xs);
    padding-bottom: var(--lumo-space-xs);
  }

  :host([theme~='small']) [part='toggle'] {
    margin-right: calc(var(--lumo-space-xs) / 2);
  }

  :host([theme~='small']) [part\$='content'] {
    font-size: var(--lumo-font-size-s);
  }

  :host([theme~='reverse']) [part='summary'] {
    justify-content: space-between;
  }

  :host([theme~='reverse']) [part='toggle'] {
    order: 1;
    margin-right: 0;
  }

  :host([theme~='reverse'][theme~='filled']) [part='summary'] {
    padding-left: var(--lumo-space-m);
  }

  /* RTL specific styles */
  :host([dir='rtl']) [part='toggle'] {
    margin-left: var(--lumo-space-xs);
    margin-right: calc(var(--lumo-space-xs) * -1);
  }

  :host([dir='rtl']) [part='toggle']::before {
    content: var(--lumo-icons-angle-left);
  }

  :host([opened][dir='rtl']) [part='toggle'] {
    transform: rotate(-90deg);
  }

  :host([theme~='small'][dir='rtl']) [part='toggle'] {
    margin-left: calc(var(--lumo-space-xs) / 2);
  }

  :host([theme~='reverse'][dir='rtl']) [part='toggle'] {
    margin-left: 0;
  }

  :host([theme~='reverse'][theme~='filled'][dir='rtl']) [part='summary'] {
    padding-right: var(--lumo-space-m);
  }
`;u("vaadin-details",Rs,{moduleId:"lumo-details"});const Sa=p`
  :host {
    margin: 0;
    border-bottom: solid 1px var(--lumo-contrast-10pct);
  }

  :host(:last-child) {
    border-bottom: none;
  }

  :host([theme~='filled']) {
    border-bottom: none;
  }

  :host([theme~='filled']:not(:last-child)) {
    margin-bottom: 2px;
  }
`;u("vaadin-accordion-panel",[Rs,Sa],{moduleId:"lumo-accordion-panel"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ia=i=>class extends ct(ut(i)){static get properties(){return{tabindex:{type:Number,value:0}}}_onKeyDown(t){super._onKeyDown(t),!t.defaultPrevented&&t.keyCode===9&&t.shiftKey&&HTMLElement.prototype.focus.apply(this)}_shouldSetFocus(t){if(!this.disabled&&this.focusElement){const s=t.composedPath();if(s[0]===this&&this._keyboardActive&&this.focusElement.focus(),s[0]===this||s.includes(this.focusElement))return!0}return!1}_tabindexChanged(t){t!==void 0&&(this.focusElement.tabIndex=t),this.disabled&&t&&(t!==-1&&(this._lastTabIndex=t),this.tabindex=void 0)}};/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class rt extends Ia(A(y(g))){static get template(){return f`
      <style>
        :host {
          display: block;
        }

        :host([hidden]) {
          display: none !important;
        }

        [part='content'] {
          display: none;
          overflow: hidden;
        }

        [part='summary'][disabled] {
          pointer-events: none;
        }

        :host([opened]) [part='content'] {
          display: block;
          overflow: visible;
        }
      </style>
      <div role="heading">
        <div
          role="button"
          part="summary"
          on-click="_onToggleClick"
          on-keydown="_onToggleKeyDown"
          disabled$="[[disabled]]"
          aria-expanded$="[[_getAriaExpanded(opened)]]"
          aria-controls$="[[_contentId]]"
        >
          <span part="toggle" aria-hidden="true"></span>
          <span part="summary-content"><slot name="summary"></slot></span>
        </div>
      </div>
      <section id$="[[_contentId]]" part="content" aria-hidden$="[[_getAriaHidden(opened)]]">
        <slot></slot>
      </section>
    `}static get is(){return"vaadin-details"}static get properties(){return{opened:{type:Boolean,value:!1,reflectToAttribute:!0,notify:!0,observer:"_openedChanged"}}}get _collapsible(){return this.shadowRoot.querySelector('[part="content"]')}get focusElement(){return this.shadowRoot.querySelector('[part="summary"]')}ready(){super.ready(),this._contentId=`${this.constructor.is}-content-${pt()}`,this._collapsible.addEventListener("keydown",e=>{e.shiftKey&&e.keyCode===9&&e.stopPropagation()})}_getAriaExpanded(e){return e?"true":"false"}_getAriaHidden(e){return e?"false":"true"}_openedChanged(e){this._collapsible.style.maxHeight=e?"":"0px"}_onToggleClick(){this.opened=!this.opened}_onToggleKeyDown(e){[13,32].indexOf(e.keyCode)>-1&&(e.preventDefault(),this.opened=!this.opened)}}customElements.define(rt.is,rt);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class nt extends rt{static get is(){return"vaadin-accordion-panel"}}customElements.define(nt.is,nt);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ii extends y(A(g)){static get template(){return f`
      <style>
        :host {
          display: block;
        }

        :host([hidden]) {
          display: none !important;
        }
      </style>
      <slot></slot>
    `}static get is(){return"vaadin-accordion"}static get properties(){return{opened:{type:Number,value:0,notify:!0,reflectToAttribute:!0},items:{type:Array,readOnly:!0,notify:!0}}}static get observers(){return["_updateItems(items, opened)"]}constructor(){super(),this._boundUpdateOpened=this._updateOpened.bind(this)}get focused(){return this.getRootNode().activeElement}focus(){if(this._observer&&this._observer.flush(),Array.isArray(this.items)){const e=this._getAvailableIndex(0);e>=0&&this.items[e].focus()}}ready(){super.ready(),this.addEventListener("keydown",e=>this._onKeydown(e)),this._observer=new k(this,e=>{this._setItems(this._filterItems(Array.from(this.children))),this._filterItems(e.addedNodes).forEach(t=>{t.addEventListener("opened-changed",this._boundUpdateOpened)})})}_filterItems(e){return e.filter(t=>t instanceof nt)}_updateItems(e,t){if(e){const s=e[t];e.forEach(r=>{r.opened=r===s})}}_onKeydown(e){const t=e.composedPath()[0];if(!this.items.some(l=>l.focusElement===t))return;const s=this.items.indexOf(this.focused);let r,a;switch(e.key){case"ArrowUp":a=-1,r=s-1;break;case"ArrowDown":a=1,r=s+1;break;case"Home":a=1,r=0;break;case"End":a=-1,r=this.items.length-1;break}r=this._getAvailableIndex(r,a),r>=0&&(this.items[r].focus(),this.items[r].setAttribute("focus-ring",""),e.preventDefault())}_getAvailableIndex(e,t){const s=this.items.length;let r=e;for(let a=0;typeof r=="number"&&a<s;a++,r+=t||1)if(r<0?r=s-1:r>=s&&(r=0),!this.items[r].disabled)return r;return-1}_updateOpened(e){const t=this._filterItems(e.composedPath())[0],s=this.items.indexOf(t);if(e.detail.value){if(t.disabled||s===-1)return;this.opened=s}else this.items.some(r=>r.opened)||(this.opened=null)}}customElements.define(ii.is,ii);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const ka=p`
  :host {
    --vaadin-user-color-0: #df0b92;
    --vaadin-user-color-1: #650acc;
    --vaadin-user-color-2: #097faa;
    --vaadin-user-color-3: #ad6200;
    --vaadin-user-color-4: #bf16f3;
    --vaadin-user-color-5: #084391;
    --vaadin-user-color-6: #078836;
  }

  [theme~='dark'] {
    --vaadin-user-color-0: #ff66c7;
    --vaadin-user-color-1: #9d8aff;
    --vaadin-user-color-2: #8aff66;
    --vaadin-user-color-3: #ffbd66;
    --vaadin-user-color-4: #dc6bff;
    --vaadin-user-color-5: #66fffa;
    --vaadin-user-color-6: #e6ff66;
  }
`,Ds=document.createElement("template");Ds.innerHTML=`<style>${ka.toString().replace(":host","html")}</style>`;document.head.appendChild(Ds.content);const Ls=document.createElement("style");Ls.textContent="html { --vaadin-avatar-size: var(--lumo-size-m); }";document.head.appendChild(Ls);u("vaadin-avatar",p`
    :host {
      color: var(--lumo-secondary-text-color);
      background-color: var(--lumo-contrast-10pct);
      border-radius: 50%;
      outline: none;
      cursor: default;
      user-select: none;
      -webkit-tap-highlight-color: transparent;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
    }

    :host([has-color-index]) {
      color: var(--lumo-base-color);
    }

    :host([focus-ring]) {
      border-color: var(--lumo-primary-color-50pct);
    }

    [part='icon'],
    [part='abbr'] {
      fill: currentColor;
    }

    [part='abbr'] {
      font-family: var(--lumo-font-family);
      font-size: 2.4375em;
      font-weight: 500;
    }

    :host([theme~='xlarge']) [part='abbr'] {
      font-size: 2.5em;
    }

    :host([theme~='large']) [part='abbr'] {
      font-size: 2.375em;
    }

    :host([theme~='small']) [part='abbr'] {
      font-size: 2.75em;
    }

    :host([theme~='xsmall']) [part='abbr'] {
      font-size: 3em;
    }

    :host([theme~='xlarge']) {
      --vaadin-avatar-size: var(--lumo-size-xl);
    }

    :host([theme~='large']) {
      --vaadin-avatar-size: var(--lumo-size-l);
    }

    :host([theme~='small']) {
      --vaadin-avatar-size: var(--lumo-size-s);
    }

    :host([theme~='xsmall']) {
      --vaadin-avatar-size: var(--lumo-size-xs);
    }
  `,{moduleId:"lumo-avatar"});/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const zs=document.createElement("template");zs.innerHTML=`
  <style>
    @font-face {
      font-family: 'vaadin-avatar-icons';
      src: url(data:application/font-woff;charset=utf-8;base64,d09GRgABAAAAAAQAAAsAAAAABnwAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABHU1VCAAABCAAAADsAAABUIIslek9TLzIAAAFEAAAAQwAAAFZAIUmEY21hcAAAAYgAAABLAAABcOspwa1nbHlmAAAB1AAAAEUAAABMYO4o1WhlYWQAAAIcAAAALgAAADYYaAmGaGhlYQAAAkwAAAAdAAAAJAZsA1VobXR4AAACbAAAAAgAAAAIA+gAAGxvY2EAAAJ0AAAABgAAAAYAJgAAbWF4cAAAAnwAAAAeAAAAIAEOACFuYW1lAAACnAAAAUIAAAKavFDYrHBvc3QAAAPgAAAAHQAAAC52hGZ4eJxjYGRgYOBiMGCwY2BycfMJYeDLSSzJY5BiYGGAAJA8MpsxJzM9kYEDxgPKsYBpDiBmg4gCACY7BUgAeJxjYGT8wjiBgZWBgamKaQ8DA0MPhGZ8wGDIyAQUZWBlZsAKAtJcUxgcXjG+YmQO+p/FEMUcxDANKMwIkgMADiUMJQB4nGNgYGBlYGBgBmIdIGZhYGAMYWBkAAE/oCgjWJyZgQsszsKgBFbDAhJ/xfj/P4wE8lnAJAMjG8Mo4AGTMlAeOKwgmIERADU0CX0AeJxjYGIAAmYJpkgGHgYRBgZGJT1GEztGIzlGET5GKEuU8YuSpZKSpQuI+LfLv21emz9jHJQPJP7dsUywsEiwBACG8g9CAAAAeJxjYGRgYADicIOnh+P5bb4ycDO/AIow3JZ4rIJMM0swRQIpDgYmEA8AKwgJOwAAeJxjYGRgYA76nwUkXzAAAbMEAyMDKmACAE2GAskAAAAAAAAAA+gAAAAAAAAAJgAAeJxjYGRgYGBiEAViBjCLgYELCBkY/oP5DAAKuwEwAAB4nI2Qu07DMBSG//SGaCWEhMSAGDx1QU0vYyemdmDrUDEhuamTpkriyHEj9RF4B56Bh2Bg5mmY+8d4Qh3qo9jf+c45thQAt/hGgGYFuHN7s1q4YvbHbdKD5w555LmLAZ499+hfPPfxhDfPA/p33hB0rmmG+PDcwg2+PLfpfzx3yL+eu7gPHj33MAxmnvtYB6+eB/SftZTbtBjJWlppRmmki2qlkkMmzZnKGbVWpkp1Iabh5Ex1qQplpFVbsTmKqk5m1sYiNjoXC11YlWValEbvVWTDnbXlfDyOvQ8jnaOGZGyRouCfky63/AyzFBE0fYUVFBIckLnKZTOXda15s+GZulxgihCTC2eXnC3cfFNV7BfY4Mi9eT3BjNYiZh6zRyMnLdxs050xNE3panuaiD7Ezk2VmGPMiP/1h+71/ATcWYAhAAB4nGNgYoAALgbsgImRiZGZgaW0OLWIgQEACl4B2QAAAA==) format('woff');
      font-weight: normal;
      font-style: normal;
    }
  </style>
`;document.head.appendChild(zs.content);/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class at extends O(A(y(g))){static get template(){return f`
      <style>
        :host {
          display: inline-block;
          flex: none;
          border-radius: 50%;
          overflow: hidden;
          height: var(--vaadin-avatar-size, 64px);
          width: var(--vaadin-avatar-size, 64px);
          border: var(--vaadin-avatar-outline-width) solid transparent;
          margin: calc(var(--vaadin-avatar-outline-width) * -1);
          background-clip: content-box;
          --vaadin-avatar-outline-width: 2px;
        }

        img {
          height: 100%;
          width: 100%;
          object-fit: cover;
        }

        [part='icon'] {
          font-size: 5.6em;
        }

        [part='abbr'] {
          font-size: 2.2em;
        }

        [part='icon'] > text {
          font-family: 'vaadin-avatar-icons';
        }

        :host([hidden]) {
          display: none !important;
        }

        svg[hidden] {
          display: none !important;
        }

        :host([has-color-index]) {
          position: relative;
          background-color: var(--vaadin-avatar-user-color);
        }

        :host([has-color-index])::before {
          position: absolute;
          content: '';
          top: 0;
          left: 0;
          bottom: 0;
          right: 0;
          border-radius: inherit;
          box-shadow: inset 0 0 0 2px var(--vaadin-avatar-user-color);
        }
      </style>
      <img hidden$="[[!__imgVisible]]" src$="[[img]]" aria-hidden="true" on-error="__onImageLoadError" />
      <svg
        part="icon"
        hidden$="[[!__iconVisible]]"
        id="avatar-icon"
        viewBox="-50 -50 100 100"
        preserveAspectRatio="xMidYMid meet"
        aria-hidden="true"
      >
        <text dy=".35em" text-anchor="middle"></text>
      </svg>
      <svg
        part="abbr"
        hidden$="[[!__abbrVisible]]"
        id="avatar-abbr"
        viewBox="-50 -50 100 100"
        preserveAspectRatio="xMidYMid meet"
        aria-hidden="true"
      >
        <text dy=".35em" text-anchor="middle">[[abbr]]</text>
      </svg>
    `}static get is(){return"vaadin-avatar"}static get properties(){return{img:{type:String,reflectToAttribute:!0,observer:"__imgChanged"},abbr:{type:String,reflectToAttribute:!0},name:{type:String,reflectToAttribute:!0},colorIndex:{type:Number,observer:"__colorIndexChanged"},i18n:{type:Object,value:()=>({anonymous:"anonymous"})},__imgVisible:Boolean,__iconVisible:Boolean,__abbrVisible:Boolean}}static get observers(){return["__imgOrAbbrOrNameChanged(img, abbr, name)","__i18nChanged(i18n.*)"]}ready(){super.ready(),this.__updateVisibility(),!this.name&&!this.abbr&&this.__setTitle(this.name),this.hasAttribute("role")||this.setAttribute("role","button"),this.hasAttribute("tabindex")||this.setAttribute("tabindex","0")}__colorIndexChanged(e){if(e!=null){const t=`--vaadin-user-color-${e}`;Boolean(getComputedStyle(document.documentElement).getPropertyValue(t))?(this.setAttribute("has-color-index",""),this.style.setProperty("--vaadin-avatar-user-color",`var(${t})`)):(this.removeAttribute("has-color-index"),console.warn(`The CSS property --vaadin-user-color-${e} is not defined`))}else this.removeAttribute("has-color-index")}__imgChanged(){this.__imgFailedToLoad=!1}__imgOrAbbrOrNameChanged(e,t,s){if(this.__updateVisibility(),t&&t!==this.__generatedAbbr){this.__setTitle(s?`${s} (${t})`:t);return}s?this.abbr=this.__generatedAbbr=s.split(" ").map(r=>r.charAt(0)).join(""):this.abbr=void 0,this.__setTitle(s)}__i18nChanged(e){e.base&&e.base.anonymous&&(this.__oldAnonymous&&this.getAttribute("title")===this.__oldAnonymous&&this.__setTitle(),this.__oldAnonymous=e.base.anonymous)}__updateVisibility(){this.__imgVisible=!!this.img&&!this.__imgFailedToLoad,this.__abbrVisible=!this.__imgVisible&&!!this.abbr,this.__iconVisible=!this.__imgVisible&&!this.abbr}__setTitle(e){e?this.setAttribute("title",e):this.setAttribute("title",this.i18n.anonymous)}__onImageLoadError(){this.img&&(console.warn(`<vaadin-avatar> The specified image could not be loaded: ${this.img}`),this.__imgFailedToLoad=!0,this.__updateVisibility())}}customElements.define(at.is,at);u("vaadin-avatar-group",p`
    :host {
      --vaadin-avatar-size: var(--lumo-size-m);
    }

    :host([theme~='xlarge']) {
      --vaadin-avatar-group-overlap: 12px;
      --vaadin-avatar-group-overlap-border: 3px;
      --vaadin-avatar-size: var(--lumo-size-xl);
    }

    :host([theme~='large']) {
      --vaadin-avatar-group-overlap: 10px;
      --vaadin-avatar-group-overlap-border: 3px;
      --vaadin-avatar-size: var(--lumo-size-l);
    }

    :host([theme~='small']) {
      --vaadin-avatar-group-overlap: 6px;
      --vaadin-avatar-group-overlap-border: 2px;
      --vaadin-avatar-size: var(--lumo-size-s);
    }

    :host([theme~='xsmall']) {
      --vaadin-avatar-group-overlap: 4px;
      --vaadin-avatar-group-overlap-border: 2px;
      --vaadin-avatar-size: var(--lumo-size-xs);
    }
  `,{moduleId:"lumo-avatar-group"});const Ta=p`
  :host {
    --_lumo-list-box-item-selected-icon-display: none;
    --_lumo-list-box-item-padding-left: calc(var(--lumo-space-m) + var(--lumo-border-radius-m) / 4);
  }

  [part='overlay'] {
    outline: none;
  }
`;u("vaadin-avatar-group-overlay",[vr,yr,Ta],{moduleId:"lumo-avatar-group-overlay"});u("vaadin-avatar-group-list-box",p`
    [part='items'] ::slotted(vaadin-item[theme='avatar-group-item']) {
      padding: var(--lumo-space-xs);
      padding-right: var(--lumo-space-m);
    }

    :host([dir='rtl']) [part='items'] ::slotted(vaadin-item[theme='avatar-group-item']) {
      padding: var(--lumo-space-xs);
      padding-left: var(--lumo-space-m);
    }
  `,{moduleId:"lumo-avatar-group-list-box"});u("vaadin-item",p`
    :host([theme='avatar-group-item']) [part='content'] {
      display: flex;
      align-items: center;
    }

    :host([theme='avatar-group-item']) ::slotted(vaadin-avatar) {
      width: var(--lumo-size-xs);
      height: var(--lumo-size-xs);
    }

    :host([theme='avatar-group-item']:not([dir='rtl'])) ::slotted(vaadin-avatar) {
      margin-right: var(--lumo-space-s);
    }

    :host([theme='avatar-group-item'][dir='rtl']) ::slotted(vaadin-avatar) {
      margin-left: var(--lumo-space-s);
    }
  `,{moduleId:"lumo-avatar-group-item"});/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class si extends Zi{static get is(){return"vaadin-avatar-group-list-box"}}customElements.define(si.is,si);/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ri extends mt(Me){static get is(){return"vaadin-avatar-group-overlay"}}customElements.define(ri.is,ri);/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ge=2;class ni extends ft(A(y(g))){static get template(){return f`
      <style>
        :host {
          display: block;
          width: 100%; /* prevent collapsing inside non-stretching column flex */
          --vaadin-avatar-group-overlap: 8px;
          --vaadin-avatar-group-overlap-border: 2px;
        }

        :host([hidden]) {
          display: none !important;
        }

        [part='container'] {
          display: flex;
          position: relative;
          width: 100%;
          flex-wrap: nowrap;
        }

        [part='avatar']:not(:first-child) {
          -webkit-mask-image: url('data:image/svg+xml;utf8,<svg viewBox=%220 0 300 300%22 fill=%22none%22 xmlns=%22http://www.w3.org/2000/svg%22><path fill-rule=%22evenodd%22 clip-rule=%22evenodd%22 d=%22M300 0H0V300H300V0ZM150 200C177.614 200 200 177.614 200 150C200 122.386 177.614 100 150 100C122.386 100 100 122.386 100 150C100 177.614 122.386 200 150 200Z%22 fill=%22black%22/></svg>');
          mask-image: url('data:image/svg+xml;utf8,<svg viewBox=%220 0 300 300%22 fill=%22none%22 xmlns=%22http://www.w3.org/2000/svg%22><path fill-rule=%22evenodd%22 clip-rule=%22evenodd%22 d=%22M300 0H0V300H300V0ZM150 200C177.614 200 200 177.614 200 150C200 122.386 177.614 100 150 100C122.386 100 100 122.386 100 150C100 177.614 122.386 200 150 200Z%22 fill=%22black%22/></svg>');
          -webkit-mask-size: calc(
            300% + var(--vaadin-avatar-group-overlap-border) * 6 - var(--vaadin-avatar-outline-width) * 6
          );
          mask-size: calc(
            300% + var(--vaadin-avatar-group-overlap-border) * 6 - var(--vaadin-avatar-outline-width) * 6
          );
        }

        [part='avatar']:not([dir='rtl']):not(:first-child) {
          margin-left: calc(var(--vaadin-avatar-group-overlap) * -1 - var(--vaadin-avatar-outline-width));
          -webkit-mask-position: calc(50% - var(--vaadin-avatar-size) + var(--vaadin-avatar-group-overlap));
          mask-position: calc(50% - var(--vaadin-avatar-size) + var(--vaadin-avatar-group-overlap));
        }

        [part='avatar'][dir='rtl']:not(:first-child) {
          margin-right: calc(var(--vaadin-avatar-group-overlap) * -1);
          -webkit-mask-position: calc(
            50% + var(--vaadin-avatar-size) - var(--vaadin-avatar-group-overlap) + var(--vaadin-avatar-outline-width)
          );
          mask-position: calc(
            50% + var(--vaadin-avatar-size) - var(--vaadin-avatar-group-overlap) + var(--vaadin-avatar-outline-width)
          );
        }
      </style>
      <div id="container" part="container">
        <template id="items" is="dom-repeat" items="[[__computeItems(items.*, __itemsInView, maxItemsVisible)]]">
          <vaadin-avatar
            name="[[item.name]]"
            abbr="[[item.abbr]]"
            img="[[item.img]]"
            part="avatar"
            theme$="[[_theme]]"
            i18n="[[i18n]]"
            color-index="[[item.colorIndex]]"
          ></vaadin-avatar>
        </template>
        <vaadin-avatar
          id="overflow"
          part="avatar"
          hidden$="[[__computeMoreHidden(items.length, __itemsInView, __maxReached)]]"
          abbr="[[__computeMore(items.length, __itemsInView, maxItemsVisible)]]"
          theme$="[[_theme]]"
          on-click="_onOverflowClick"
          on-keydown="_onOverflowKeyDown"
          aria-haspopup="listbox"
        ></vaadin-avatar>
      </div>
      <vaadin-avatar-group-overlay
        id="overlay"
        opened="{{_opened}}"
        no-vertical-overlap
        on-vaadin-overlay-close="_onVaadinOverlayClose"
      >
        <template>
          <vaadin-avatar-group-list-box on-keydown="_onListKeyDown">
            <template is="dom-repeat" items="[[__computeExtraItems(items.*, __itemsInView, maxItemsVisible)]]">
              <vaadin-item theme="avatar-group-item" role="option">
                <vaadin-avatar
                  name="[[item.name]]"
                  abbr="[[item.abbr]]"
                  img="[[item.img]]"
                  i18n="[[i18n]]"
                  part="avatar"
                  theme$="[[_theme]]"
                  color-index="[[item.colorIndex]]"
                  tabindex="-1"
                  aria-hidden="true"
                ></vaadin-avatar>
                [[item.name]]
              </vaadin-item>
            </template>
          </vaadin-avatar-group-list-box>
        </template>
      </vaadin-avatar-group-overlay>
    `}static get is(){return"vaadin-avatar-group"}static get properties(){return{items:{type:Array},maxItemsVisible:{type:Number},i18n:{type:Object,value:()=>({anonymous:"anonymous",activeUsers:{one:"Currently one active user",many:"Currently {count} active users"},joined:"{user} joined",left:"{user} left"})},__maxReached:{type:Boolean,computed:"__computeMaxReached(items.length, maxItemsVisible)"},__itemsInView:{type:Number,value:null},_opened:{type:Boolean,observer:"__openedChanged",value:!1}}}static get observers(){return["__computeMoreTitle(items.length, __itemsInView, maxItemsVisible)","__itemsChanged(items.splices, items.*)","__i18nItemsChanged(i18n.*, items.length)"]}ready(){super.ready(),this._overlayElement=this.shadowRoot.querySelector("vaadin-avatar-group-overlay"),this._overlayElement.positionTarget=this.$.overflow,br(this,()=>{this.__setItemsInView()})}disconnectedCallback(){super.disconnectedCallback(),this._opened=!1}get _avatars(){return this.shadowRoot.querySelectorAll("vaadin-avatar")}__getMessage(e,t){return t.replace("{user}",e.name||e.abbr||this.i18n.anonymous)}_onOverflowClick(e){e.stopPropagation(),this._opened?this.$.overlay.close():e.defaultPrevented||(this._opened=!0)}_onOverflowKeyDown(e){this._opened||/^(Enter|SpaceBar|\s)$/.test(e.key)&&(e.preventDefault(),this._opened=!0)}_onListKeyDown(e){(e.key==="Escape"||e.key==="Esc"||/^(Tab)$/.test(e.key))&&(this._opened=!1)}_onResize(){this.__setItemsInView()}_onVaadinOverlayClose(e){e.detail.sourceEvent&&e.detail.sourceEvent.composedPath().includes(this)&&e.preventDefault()}__computeItems(e,t,s){const r=e.base||[],a=this.__getLimit(r.length,t,s);return a?r.slice(0,a):r}__computeExtraItems(e,t,s){const r=e.base||[],a=this.__getLimit(r.length,t,s);return a?r.slice(a):r}__computeMaxReached(e,t){return t!=null&&e>this.__getMax(t)}__computeMore(e,t,s){return`+${e-this.__getLimit(e,t,s)}`}__computeMoreHidden(e,t,s){return!s&&!(t&&t<e)}__computeMoreTitle(e,t,s){const r=this.__getLimit(e,t,s);if(r==null)return;const a=[];for(let l=r;l<e;l++){const n=this.items[l];n&&a.push(n.name||n.abbr||"anonymous")}this.$.overflow.setAttribute("title",a.join(`
`))}__getLimit(e,t,s){let r=null;const a=this.__getMax(s);return s!=null&&a<e?r=a-1:t&&t<e&&(r=t),Math.min(r,this.__calculateAvatarsFitWidth())}__getMax(e){return Math.max(e,Ge)}__itemsChanged(e,t){const s=t.base;this.$.items.render(),this.__setItemsInView(),e&&Array.isArray(e.indexSplices)?e.indexSplices.forEach(r=>{this.__announceItemsChange(s,r)}):Array.isArray(s)&&Array.isArray(this.__oldItems)&&ht(s,this.__oldItems).forEach(a=>{this.__announceItemsChange(s,a)}),this.__oldItems=s}__announceItemsChange(e,t){const{addedCount:s,index:r,removed:a}=t;let l=[],n=[];s&&(l=e.slice(r,r+s).map(d=>this.__getMessage(d,this.i18n.joined||"{user} joined"))),a&&(n=a.map(d=>this.__getMessage(d,this.i18n.left||"{user} left")));const o=n.concat(l);o.length>0&&V(o.join(", "))}__i18nItemsChanged(e,t){const{base:s}=e;if(s&&s.activeUsers){const r=t===1?"one":"many";s.activeUsers[r]&&this.setAttribute("aria-label",s.activeUsers[r].replace("{count}",t||0))}}__openedChanged(e,t){e?(this._menuElement||(this._menuElement=this._overlayElement.content.querySelector("vaadin-avatar-group-list-box"),this._menuElement.setAttribute("role","listbox")),this._openedWithFocusRing=this.$.overflow.hasAttribute("focus-ring"),this._menuElement.querySelectorAll("vaadin-avatar").forEach(r=>r.removeAttribute("title")),this._menuElement.focus()):t&&(this.$.overflow.focus(),this._openedWithFocusRing&&this.$.overflow.setAttribute("focus-ring","")),this.$.overflow.setAttribute("aria-expanded",e===!0)}__setItemsInView(){const e=this._avatars,t=this.items;if(!t||!e||e.length<3)return;let s=this.__calculateAvatarsFitWidth();s===t.length-1&&(s=t.length),s>=t.length&&this._opened&&(this.$.overlay.close(),this.$.overlay._flushAnimation("closing")),this.__itemsInView=s}__calculateAvatarsFitWidth(){if(!this.shadowRoot||this._avatars.length<Ge)return Ge;const e=this._avatars,t=e[0].clientWidth,{marginLeft:s,marginRight:r}=getComputedStyle(e[1]),a=this.getAttribute("dir")==="rtl"?parseInt(r,0)-parseInt(s,0):parseInt(s,0)-parseInt(r,0);return Math.floor((this.$.container.offsetWidth-t)/(t+a))}}customElements.define(ni.is,ni);const Pa=p`
  :host {
    color: var(--lumo-body-text-color);
    font-size: var(--lumo-font-size-m);
    font-family: var(--lumo-font-family);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    -webkit-tap-highlight-color: transparent;
    padding: var(--lumo-space-xs) 0;
  }

  :host::before {
    /* Effective height of vaadin-checkbox */
    height: var(--lumo-size-s);
    box-sizing: border-box;
    display: inline-flex;
    align-items: center;
  }

  :host([theme~='vertical']) [part='group-field'] {
    display: flex;
    flex-direction: column;
  }

  :host([disabled]) [part='label'] {
    color: var(--lumo-disabled-text-color);
    -webkit-text-fill-color: var(--lumo-disabled-text-color);
  }

  :host([focused]:not([disabled])) [part='label'] {
    color: var(--lumo-primary-text-color);
  }

  :host(:hover:not([disabled]):not([focused])) [part='label'],
  :host(:hover:not([disabled]):not([focused])) [part='helper-text'] {
    color: var(--lumo-body-text-color);
  }

  /* Touch device adjustment */
  @media (pointer: coarse) {
    :host(:hover:not([disabled]):not([focused])) [part='label'] {
      color: var(--lumo-secondary-text-color);
    }
  }
`;u("vaadin-checkbox-group",[Fe,Oe,Pa],{moduleId:"lumo-checkbox-group"});/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ai extends de(O(Re(A(y(g))))){static get is(){return"vaadin-checkbox-group"}static get template(){return f`
      <style>
        :host {
          display: inline-flex;
        }

        :host::before {
          content: '\\2003';
          width: 0;
          display: inline-block;
        }

        :host([hidden]) {
          display: none !important;
        }

        .vaadin-group-field-container {
          display: flex;
          flex-direction: column;
          width: 100%;
        }

        :host(:not([has-label])) [part='label'] {
          display: none;
        }
      </style>

      <div class="vaadin-group-field-container">
        <div part="label">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true"></span>
        </div>

        <div part="group-field">
          <slot></slot>
        </div>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get properties(){return{value:{type:Array,value:()=>[],notify:!0,observer:"__valueChanged"}}}constructor(){super(),this.__registerCheckbox=this.__registerCheckbox.bind(this),this.__unregisterCheckbox=this.__unregisterCheckbox.bind(this),this.__onCheckboxCheckedChanged=this.__onCheckboxCheckedChanged.bind(this)}ready(){super.ready(),this.ariaTarget=this,this.setAttribute("role","group"),this._observer=new k(this,({addedNodes:e,removedNodes:t})=>{const s=this.__filterCheckboxes(e),r=this.__filterCheckboxes(t);s.forEach(this.__registerCheckbox),r.forEach(this.__unregisterCheckbox),this.__warnOfCheckboxesWithoutValue(s)})}checkValidity(){return!this.required||this.value.length>0}__filterCheckboxes(e){return e.filter(t=>t instanceof Ar)}get __checkboxes(){return this.__filterCheckboxes([...this.children])}__warnOfCheckboxesWithoutValue(e){e.some(s=>{const{value:r}=s;return!s.hasAttribute("value")&&(!r||r==="on")})&&console.warn("Please provide the value attribute to all the checkboxes inside the checkbox group.")}__registerCheckbox(e){e.addEventListener("checked-changed",this.__onCheckboxCheckedChanged),this.disabled&&(e.disabled=!0),e.checked?this.__addCheckboxToValue(e.value):this.value.includes(e.value)&&(e.checked=!0)}__unregisterCheckbox(e){e.removeEventListener("checked-changed",this.__onCheckboxCheckedChanged),e.checked&&this.__removeCheckboxFromValue(e.value)}_disabledChanged(e,t){super._disabledChanged(e,t),!(!e&&t===void 0)&&t!==e&&this.__checkboxes.forEach(s=>{s.disabled=e})}__addCheckboxToValue(e){this.value.includes(e)||(this.value=[...this.value,e])}__removeCheckboxFromValue(e){this.value.includes(e)&&(this.value=this.value.filter(t=>t!==e))}__onCheckboxCheckedChanged(e){const t=e.target;t.checked?this.__addCheckboxToValue(t.value):this.__removeCheckboxFromValue(t.value)}__valueChanged(e,t){e.length===0&&t===void 0||(this.toggleAttribute("has-value",e.length>0),this.__checkboxes.forEach(s=>{s.checked=e.includes(s.value)}),t!==void 0&&this.validate())}_shouldRemoveFocus(e){return!this.contains(e.relatedTarget)}_setFocused(e){super._setFocused(e),e||this.validate()}}customElements.define(ai.is,ai);u("vaadin-confirm-dialog-overlay",p`
    [part='header'] ::slotted(h3) {
      margin-top: 0 !important;
      margin-bottom: 0 !important;
      margin-inline-start: calc(var(--lumo-space-l) - var(--lumo-space-m));
    }

    [part='message'] {
      width: 25em;
      min-width: 100%;
      max-width: 100%;
    }

    ::slotted([slot$='button'][theme~='tertiary']) {
      padding-left: var(--lumo-space-s);
      padding-right: var(--lumo-space-s);
    }

    [part='cancel-button'] {
      flex-grow: 1;
    }

    @media (max-width: 360px) {
      [part='footer'] {
        flex-direction: column-reverse;
        align-items: stretch;
        padding: var(--lumo-space-s) var(--lumo-space-l);
        gap: var(--lumo-space-s);
      }

      ::slotted([slot$='button']) {
        width: 100%;
        margin: 0;
      }
    }
  `,{moduleId:"lumo-confirm-dialog-overlay"});/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-confirm-dialog-overlay",p`
    :host {
      --_vaadin-confirm-dialog-content-width: auto;
      --_vaadin-confirm-dialog-content-height: auto;
    }

    [part='overlay'] {
      width: var(--_vaadin-confirm-dialog-content-width);
      height: var(--_vaadin-confirm-dialog-content-height);
    }

    /* Make buttons clickable */
    [part='footer'] > * {
      pointer-events: all;
    }
  `,{moduleId:"vaadin-confirm-dialog-overlay-styles"});let N;const Ba=f`
  <div part="cancel-button">
    <slot name="cancel-button"></slot>
  </div>
  <div part="reject-button">
    <slot name="reject-button"></slot>
  </div>
  <div part="confirm-button">
    <slot name="confirm-button"></slot>
  </div>
`;class oi extends xr{static get is(){return"vaadin-confirm-dialog-overlay"}static get template(){if(!N){N=super.template.cloneNode(!0);const e=N.content.querySelector('[part="header"]');e.innerHTML="";const t=document.createElement("slot");t.setAttribute("name","header"),e.appendChild(t);const s=N.content.querySelector('[part="content"]'),r=s.querySelector("slot:not([name])"),a=document.createElement("div");a.setAttribute("part","message"),s.appendChild(a),a.appendChild(r);const l=N.content.querySelector('[part="footer"]');l.setAttribute("role","toolbar");const n=l.querySelector("slot");l.removeChild(n),l.appendChild(Ba.content.cloneNode(!0))}return N}_finishClosing(){super._finishClosing(),this.dispatchEvent(new CustomEvent("vaadin-confirm-dialog-close"))}_headerFooterRendererChange(e,t,s){super._headerFooterRendererChange(e,t,s),this.setAttribute("has-header",""),this.setAttribute("has-footer","")}}customElements.define(oi.is,oi);class li extends wr{static get is(){return"vaadin-confirm-dialog-dialog"}static get template(){return f`
      <style>
        :host {
          display: none;
        }
      </style>

      <vaadin-confirm-dialog-overlay
        id="overlay"
        on-opened-changed="_onOverlayOpened"
        on-mousedown="_bringOverlayToFront"
        on-touchstart="_bringOverlayToFront"
        theme$="[[_theme]]"
        modeless="[[modeless]]"
        with-backdrop="[[!modeless]]"
        resizable$="[[resizable]]"
        focus-trap
      ></vaadin-confirm-dialog-overlay>
    `}}customElements.define(li.is,li);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ns=le(i=>class extends i{get slots(){return{}}ready(){super.ready(),this._connectSlotMixin()}_connectSlotMixin(){Object.keys(this.slots).forEach(t=>{if(!(this._getDirectSlotChild(t)!==void 0)){const r=this.slots[t],a=r();a instanceof Element&&(t!==""&&a.setAttribute("slot",t),this.appendChild(a))}})}_getDirectSlotChild(t){return Array.from(this.childNodes).find(s=>s.nodeType===Node.ELEMENT_NODE&&s.slot===t||s.nodeType===Node.TEXT_NODE&&s.textContent.trim()&&t==="")}});/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class di extends Ns(A(Cr(g))){static get template(){return f`
      <style>
        :host,
        [hidden] {
          display: none !important;
        }
      </style>

      <vaadin-confirm-dialog-dialog
        id="dialog"
        opened="{{opened}}"
        aria-label="[[_getAriaLabel(header)]]"
        theme$="[[_theme]]"
        no-close-on-outside-click
        no-close-on-esc="[[noCloseOnEsc]]"
      ></vaadin-confirm-dialog-dialog>

      <div hidden>
        <slot name="header"></slot>
        <slot></slot>
        <slot name="cancel-button"></slot>
        <slot name="reject-button"></slot>
        <slot name="confirm-button"></slot>
      </div>
    `}static get is(){return"vaadin-confirm-dialog"}static get properties(){return{opened:{type:Boolean,value:!1,notify:!0},header:{type:String,value:""},message:{type:String,value:""},confirmText:{type:String,value:"Confirm"},confirmTheme:{type:String,value:"primary"},noCloseOnEsc:{type:Boolean,value:!1},reject:{type:Boolean,reflectToAttribute:!0,value:!1},rejectText:{type:String,value:"Reject"},rejectTheme:{type:String,value:"error tertiary"},cancel:{type:Boolean,reflectToAttribute:!0,value:!1},cancelText:{type:String,value:"Cancel"},cancelTheme:{type:String,value:"tertiary"},_cancelButton:{type:HTMLElement,observer:"_cancelButtonChanged"},_confirmButton:{type:HTMLElement,observer:"_confirmButtonChanged"},_headerNode:{type:HTMLElement},_messageNode:{type:HTMLElement},_rejectButton:{type:HTMLElement,observer:"_rejectButtonChanged"}}}static get observers(){return["__updateConfirmButton(_confirmButton, confirmText, confirmTheme)","__updateCancelButton(_cancelButton, cancelText, cancelTheme, cancel)","__updateHeaderNode(_headerNode, header)","__updateMessageNode(_messageNode, message)","__updateRejectButton(_rejectButton, rejectText, rejectTheme, reject)"]}get slots(){return{...super.slots,header:()=>{const e=document.createElement("h3");return this.__defaultHeader=e,e},"":()=>{const e=document.createElement("div");return this.__defaultMessage=e,e},"cancel-button":()=>{const e=document.createElement("vaadin-button");return e.setAttribute("theme",this.cancelTheme),e.textContent=this.cancelText,e._isDefaultButton=!0,e},"reject-button":()=>{const e=document.createElement("vaadin-button");return e.setAttribute("theme",this.rejectTheme),e.textContent=this.rejectText,e._isDefaultButton=!0,e},"confirm-button":()=>{const e=document.createElement("vaadin-button");return e._isDefaultButton=!0,e}}}constructor(){super(),this.__slottedNodes=[],this._observer=new k(this,e=>{this.__onDomChange(e.addedNodes)})}ready(){super.ready(),this.__boundCancel=this._cancel.bind(this),this.__boundConfirm=this._confirm.bind(this),this.__boundReject=this._reject.bind(this),this._overlayElement=this.$.dialog.$.overlay,this._overlayElement.addEventListener("vaadin-overlay-escape-press",this._escPressed.bind(this)),this._overlayElement.addEventListener("vaadin-overlay-open",()=>this.__onDialogOpened()),this._overlayElement.addEventListener("vaadin-confirm-dialog-close",()=>this.__onDialogClosed()),this._dimensions&&Object.keys(this._dimensions).forEach(e=>{this._setDimension(e,this._dimensions[e])})}__onDialogOpened(){const e=this._overlayElement;this.__slottedNodes.forEach(s=>{e.appendChild(s)});const t=e.querySelector('[slot="confirm-button"]');t&&t.focus()}__onDialogClosed(){const e=this.__slottedNodes;this.__slottedNodes=[],e.forEach(t=>{this.appendChild(t)})}__onDomChange(e){e.forEach(t=>{this.__slottedNodes.push(t);const s=t.nodeType===Node.ELEMENT_NODE,r=s?t.getAttribute("slot"):"";if(r)if(r.indexOf("button")>=0){const[a]=r.split("-");this[`_${a}Button`]=t}else r==="header"&&(this._headerNode=t);else(t.nodeType===Node.TEXT_NODE&&t.textContent.trim()!==""||s&&t.slot==="")&&(this._messageNode=t)})}_cancelButtonChanged(e,t){this.__setupSlottedButton(e,t,this.__boundCancel)}_confirmButtonChanged(e,t){this.__setupSlottedButton(e,t,this.__boundConfirm)}_rejectButtonChanged(e,t){this.__setupSlottedButton(e,t,this.__boundReject)}__setupSlottedButton(e,t,s){t&&t.parentElement&&t.remove(),e.addEventListener("click",s)}__updateCancelButton(e,t,s,r){e&&(e._isDefaultButton&&(e.textContent=t,e.setAttribute("theme",s)),e.toggleAttribute("hidden",!r))}__updateConfirmButton(e,t,s){e&&e._isDefaultButton&&(e.textContent=t,e.setAttribute("theme",s))}__updateHeaderNode(e,t){e&&e===this.__defaultHeader&&(e.textContent=t)}__updateMessageNode(e,t){e&&e===this.__defaultMessage&&(e.textContent=t)}__updateRejectButton(e,t,s,r){e&&(e._isDefaultButton&&(e.textContent=t,e.setAttribute("theme",s)),e.toggleAttribute("hidden",!r))}_escPressed(e){e.defaultPrevented||this._cancel()}_confirm(){this.dispatchEvent(new CustomEvent("confirm")),this.opened=!1}_cancel(){this.dispatchEvent(new CustomEvent("cancel")),this.opened=!1}_reject(){this.dispatchEvent(new CustomEvent("reject")),this.opened=!1}_getAriaLabel(e){return e||"confirmation"}_setWidth(e){this._setDimensionIfAttached("width",e)}_setHeight(e){this._setDimensionIfAttached("height",e)}_setDimensionIfAttached(e,t){this._overlayElement?this._setDimension(e,t):(this._dimensions=this._dimensions||{},this._dimensions[e]=t)}_setDimension(e,t){this._overlayElement.style.setProperty(`--_vaadin-confirm-dialog-content-${e}`,t)}}customElements.define(di.is,di);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Vs=p`
  :host {
    --lumo-text-field-size: var(--lumo-size-m);
    color: var(--lumo-body-text-color);
    font-size: var(--lumo-font-size-m);
    /* align with text-field height + vertical paddings */
    line-height: calc(var(--lumo-text-field-size) + 2 * var(--lumo-space-xs));
    font-family: var(--lumo-font-family);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    -webkit-tap-highlight-color: transparent;
    padding: 0;
  }

  :host::before {
    margin-top: var(--lumo-space-xs);
    height: var(--lumo-text-field-size);
    box-sizing: border-box;
    display: inline-flex;
    align-items: center;
  }

  /* align with text-field label */
  :host([has-label]) [part='label'] {
    padding-bottom: calc(0.5em - var(--lumo-space-xs));
  }

  :host(:not([has-label])) [part='label'],
  :host(:not([has-label]))::before {
    display: none;
  }

  /* align with text-field error message */
  :host([has-error-message]) [part='error-message']::before {
    height: calc(0.4em - var(--lumo-space-xs));
  }

  :host([focused]:not([readonly]):not([disabled])) [part='label'] {
    color: var(--lumo-primary-text-color);
  }

  :host(:hover:not([readonly]):not([disabled]):not([focused])) [part='label'],
  :host(:hover:not([readonly]):not([disabled]):not([focused])) [part='helper-text'] {
    color: var(--lumo-body-text-color);
  }

  /* Touch device adjustment */
  @media (pointer: coarse) {
    :host(:hover:not([readonly]):not([disabled]):not([focused])) [part='label'] {
      color: var(--lumo-secondary-text-color);
    }
  }

  /* Disabled */
  :host([disabled]) [part='label'] {
    color: var(--lumo-disabled-text-color);
    -webkit-text-fill-color: var(--lumo-disabled-text-color);
  }

  /* Small theme */
  :host([theme~='small']) {
    font-size: var(--lumo-font-size-s);
    --lumo-text-field-size: var(--lumo-size-s);
  }

  :host([theme~='small'][has-label]) [part='label'] {
    font-size: var(--lumo-font-size-xs);
  }

  :host([theme~='small'][has-label]) [part='error-message'] {
    font-size: var(--lumo-font-size-xxs);
  }

  /* When custom-field is used with components without outer margin */
  :host([theme~='whitespace'][has-label]) [part='label'] {
    padding-bottom: 0.5em;
  }
`;u("vaadin-custom-field",[Fe,Oe,Vs],{moduleId:"lumo-custom-field"});/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class hi extends de(O(ut(y(A(g))))){static get is(){return"vaadin-custom-field"}static get template(){return f`
      <style>
        :host {
          display: inline-flex;
        }

        :host::before {
          content: '\\2003';
          width: 0;
          display: inline-block;
          /* Size and position this element on the same vertical position as the input-field element
           to make vertical align for the host element work as expected */
        }

        :host([hidden]) {
          display: none !important;
        }

        .vaadin-custom-field-container {
          width: 100%;
          display: flex;
          flex-direction: column;
        }

        .inputs-wrapper {
          flex: none;
        }
      </style>

      <div class="vaadin-custom-field-container">
        <div part="label" on-click="focus">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true"></span>
        </div>

        <div class="inputs-wrapper" on-change="__onInputChange">
          <slot id="slot"></slot>
        </div>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get properties(){return{name:String,value:{type:String,observer:"__valueChanged",notify:!0},inputs:{type:Array,readOnly:!0},i18n:{type:Object,value:()=>({parseValue(e){return e.split("	")},formatValue(e){return e.join("	")}})}}}connectedCallback(){super.connectedCallback(),this.__observer&&this.__observer.connect()}disconnectedCallback(){super.disconnectedCallback(),this.__observer&&this.__observer.disconnect()}ready(){super.ready(),this.setAttribute("role","group"),this.ariaTarget=this,this.__setInputsFromSlot(),this.__observer=new k(this.$.slot,()=>{this.__setInputsFromSlot()})}focus(){this.inputs&&this.inputs[0]&&this.inputs[0].focus()}_setFocused(e){super._setFocused(e),e||this.validate()}_shouldRemoveFocus(e){const{relatedTarget:t}=e;return!this.inputs.some(s=>t===(s.focusElement||s))}checkValidity(){return!(this.inputs.filter(t=>!(t.validate||t.checkValidity).call(t)).length||this.required&&!this.value.trim())}_onKeyDown(e){e.key==="Tab"&&(this.inputs.indexOf(e.target)<this.inputs.length-1&&!e.shiftKey||this.inputs.indexOf(e.target)>0&&e.shiftKey?this.dispatchEvent(new CustomEvent("internal-tab")):this.__setValue())}__onInputChange(e){e.stopPropagation(),this.__setValue(),this.validate(),this.dispatchEvent(new CustomEvent("change",{bubbles:!0,cancelable:!1,detail:{value:this.value}}))}__setValue(){this.__settingValue=!0,this.value=this.i18n.formatValue.apply(this,[this.inputs.map(e=>e.value)]),this.__settingValue=!1}__queryAllAssignedElements(e){const t=[];let s;return e.tagName==="SLOT"?s=e.assignedElements({flatten:!0}):(t.push(e),s=Array.from(e.children)),s.forEach(r=>t.push(...this.__queryAllAssignedElements(r))),t}__isInput(e){return!(e.getAttribute("slot")==="input"||e.getAttribute("slot")==="textarea")&&(e.validate||e.checkValidity)}__getInputsFromSlot(){return this.__queryAllAssignedElements(this.$.slot).filter(e=>this.__isInput(e))}__setInputsFromSlot(){this._setInputs(this.__getInputsFromSlot()),this.__setValue()}__toggleHasValue(e){this.toggleAttribute("has-value",e!==null&&e.trim()!=="")}__valueChanged(e,t){if(this.__settingValue||!this.inputs)return;this.__toggleHasValue(e);const s=this.i18n.parseValue(e);if(!s||s.length===0){console.warn("Value parser has not provided values array");return}this.inputs.forEach((r,a)=>{r.value=s[a]}),t!==void 0&&this.validate()}}customElements.define(hi.is,hi);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ma=p`
  [part~='toggle-button']::before {
    content: var(--lumo-icons-clock);
  }

  :host([dir='rtl']) [part='input-field'] ::slotted(input:placeholder-shown) {
    --_lumo-text-field-overflow-mask-image: none;
  }

  :host([dir='rtl']) [part='input-field'] ::slotted(input) {
    --_lumo-text-field-overflow-mask-image: linear-gradient(to left, transparent, #000 1.25em);
  }
`;u("vaadin-time-picker",[De,Ma],{moduleId:"lumo-time-picker"});/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ci extends Er{static get is(){return"vaadin-time-picker-item"}}customElements.define(ci.is,ci);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ui extends Sr{static get is(){return"vaadin-time-picker-scroller"}}customElements.define(ui.is,ui);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-time-picker-overlay",p`
    #overlay {
      width: var(--vaadin-time-picker-overlay-width, var(--_vaadin-time-picker-overlay-default-width, auto));
    }
  `,{moduleId:"vaadin-time-picker-overlay-styles"});class pi extends Ir{static get is(){return"vaadin-time-picker-overlay"}}customElements.define(pi.is,pi);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class mi extends kr(y(g)){static get is(){return"vaadin-time-picker-combo-box"}static get template(){return f`
      <style>
        :host([opened]) {
          pointer-events: auto;
        }
      </style>

      <slot></slot>

      <vaadin-time-picker-overlay
        id="overlay"
        opened="[[_overlayOpened]]"
        loading$="[[loading]]"
        theme$="[[_theme]]"
        position-target="[[positionTarget]]"
        no-vertical-overlap
        restore-focus-node="[[inputElement]]"
      ></vaadin-time-picker-overlay>
    `}static get properties(){return{positionTarget:{type:Object}}}get _tagNamePrefix(){return"vaadin-time-picker"}get clearElement(){return this.querySelector('[part="clear-button"]')}ready(){super.ready(),this.allowCustomValue=!0,this._toggleElement=this.querySelector(".toggle-button"),this.setAttribute("dir","ltr")}}customElements.define(mi.is,mi);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const fi="00:00:00.000",_i="23:59:59.999";u("vaadin-time-picker",gt,{moduleId:"vaadin-time-picker-styles"});class W extends Xi(Tr(y(A(g)))){static get is(){return"vaadin-time-picker"}static get template(){return f`
      <style>
        /* See https://github.com/vaadin/vaadin-time-picker/issues/145 */
        :host([dir='rtl']) [part='input-field'] {
          direction: ltr;
        }

        :host([dir='rtl']) [part='input-field'] ::slotted(input)::placeholder {
          direction: rtl;
          text-align: left;
        }

        [part~='toggle-button'] {
          cursor: pointer;
        }
      </style>

      <div class="vaadin-time-picker-container">
        <div part="label">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true" on-click="focus"></span>
        </div>

        <vaadin-time-picker-combo-box
          id="comboBox"
          filtered-items="[[__dropdownItems]]"
          value="{{_comboBoxValue}}"
          disabled="[[disabled]]"
          readonly="[[readonly]]"
          clear-button-visible="[[clearButtonVisible]]"
          auto-open-disabled="[[autoOpenDisabled]]"
          position-target="[[_inputContainer]]"
          theme$="[[_theme]]"
          on-change="__onComboBoxChange"
        >
          <vaadin-input-container
            part="input-field"
            readonly="[[readonly]]"
            disabled="[[disabled]]"
            invalid="[[invalid]]"
            theme$="[[_theme]]"
          >
            <slot name="prefix" slot="prefix"></slot>
            <slot name="input"></slot>
            <div id="clearButton" part="clear-button" slot="suffix" aria-hidden="true"></div>
            <div id="toggleButton" class="toggle-button" part="toggle-button" slot="suffix" aria-hidden="true"></div>
          </vaadin-input-container>
        </vaadin-time-picker-combo-box>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get properties(){return{value:{type:String,notify:!0,value:""},min:{type:String,value:""},max:{type:String,value:""},step:{type:Number},autoOpenDisabled:Boolean,__dropdownItems:{type:Array},i18n:{type:Object,value:()=>({formatTime:e=>{if(!e)return;const t=(r=0,a="00")=>(a+r).substr((a+r).length-a.length);let s=`${t(e.hours)}:${t(e.minutes)}`;return e.seconds!==void 0&&(s+=`:${t(e.seconds)}`),e.milliseconds!==void 0&&(s+=`.${t(e.milliseconds,"000")}`),s},parseTime:e=>{const t="(\\d|[0-1]\\d|2[0-3])",s="(\\d|[0-5]\\d)",r=s,a="(\\d{1,3})",n=new RegExp(`^${t}(?::${s}(?::${r}(?:\\.${a})?)?)?$`).exec(e);if(n){if(n[4])for(;n[4].length<3;)n[4]+="0";return{hours:n[1],minutes:n[2],seconds:n[3],milliseconds:n[4]}}}})},_comboBoxValue:{type:String,observer:"__comboBoxValueChanged"},_inputContainer:Object}}static get observers(){return["__updateDropdownItems(i18n.*, min, max, step)"]}static get constraints(){return[...super.constraints,"min","max"]}get clearElement(){return this.$.clearButton}ready(){super.ready(),this.addController(new es(this,e=>{this._setInputElement(e),this._setFocusElement(e),this.stateTarget=e,this.ariaTarget=e})),this.addController(new _t(this.inputElement,this._labelController)),this._inputContainer=this.shadowRoot.querySelector('[part~="input-field"]')}_inputElementChanged(e){super._inputElementChanged(e),e&&this.$.comboBox._setInputElement(e)}checkValidity(){return!!(this.inputElement.checkValidity()&&(!this.value||this._timeAllowed(this.i18n.parseTime(this.value)))&&(!this._comboBoxValue||this.i18n.parseTime(this._comboBoxValue)))}_setFocused(e){super._setFocused(e),e||this.validate()}__validDayDivisor(e){return!e||24*3600%e===0||e<1&&e%1*1e3%1===0}_onKeyDown(e){if(super._onKeyDown(e),this.readonly||this.disabled||this.__dropdownItems.length)return;const t=this.__validDayDivisor(this.step)&&this.step||60;e.keyCode===40?this.__onArrowPressWithStep(-t):e.keyCode===38&&this.__onArrowPressWithStep(t)}_onEscape(){}__onArrowPressWithStep(e){const t=this.__addStep(this.__getMsec(this.__memoValue),e,!0);this.__memoValue=t,this.inputElement.value=this.i18n.formatTime(this.__validateTime(t)),this.__dispatchChange()}__dispatchChange(){this.dispatchEvent(new CustomEvent("change",{bubbles:!0}))}__getMsec(e){let t=(e&&e.hours||0)*60*60*1e3;return t+=(e&&e.minutes||0)*60*1e3,t+=(e&&e.seconds||0)*1e3,t+=e&&parseInt(e.milliseconds)||0,t}__getSec(e){let t=(e&&e.hours||0)*60*60;return t+=(e&&e.minutes||0)*60,t+=e&&e.seconds||0,t+=e&&e.milliseconds/1e3||0,t}__addStep(e,t,s){e===0&&t<0&&(e=24*60*60*1e3);const r=t*1e3,a=e%r;r<0&&a&&s?e-=a:r>0&&a&&s?e-=a-r:e+=r;const l=Math.floor(e/1e3/60/60);e-=l*1e3*60*60;const n=Math.floor(e/1e3/60);e-=n*1e3*60;const o=Math.floor(e/1e3);return e-=o*1e3,{hours:l<24?l:0,minutes:n,seconds:o,milliseconds:e}}__updateDropdownItems(e,t,s,r){const a=this.__validateTime(this.__parseISO(t||fi)),l=this.__getSec(a),n=this.__validateTime(this.__parseISO(s||_i)),o=this.__getSec(n);if(this.__adjustValue(l,o,a,n),this.__dropdownItems=this.__generateDropdownList(l,o,r),r!==this.__oldStep){this.__oldStep=r;const d=this.__validateTime(this.__parseISO(this.value));this.__updateValue(d)}this.value&&(this._comboBoxValue=this.i18n.formatTime(this.i18n.parseTime(this.value)))}__generateDropdownList(e,t,s){if(s<15*60||!this.__validDayDivisor(s))return[];const r=[];s=s||3600;let a=-s+e;for(;a+s>=e&&a+s<=t;){const l=this.__validateTime(this.__addStep(a*1e3,s));a+=s;const n=this.i18n.formatTime(l);r.push({label:n,value:n})}return r}__adjustValue(e,t,s,r){if(!this.__memoValue)return;const a=this.__getSec(this.__memoValue);a<e?this.__updateValue(s):a>t&&this.__updateValue(r)}_valueChanged(e,t){const s=this.__memoValue=this.__parseISO(e),r=this.__formatISO(s)||"";e!==""&&e!==null&&!s?this.value=t===void 0?"":t:e!==r?this.value=r:this.__keepInvalidInput?delete this.__keepInvalidInput:this.__updateInputValue(s),this._toggleHasValue(this._hasValue)}__comboBoxValueChanged(e,t){if(e===""&&t===void 0)return;const s=this.i18n.parseTime(e),r=this.i18n.formatTime(s)||"";s?e!==r?this._comboBoxValue=r:this.__updateValue(s):(e!==""&&(this.__keepInvalidInput=!0),this.value="")}__onComboBoxChange(e){e.stopPropagation(),this.validate(),this.__dispatchChange()}__updateValue(e){const t=this.__formatISO(this.__validateTime(e))||"";this.value=t}__updateInputValue(e){const t=this.i18n.formatTime(this.__validateTime(e))||"";this._comboBoxValue=t}__validateTime(e){return e&&(e.hours=parseInt(e.hours),e.minutes=parseInt(e.minutes||0),e.seconds=this.__stepSegment<3?void 0:parseInt(e.seconds||0),e.milliseconds=this.__stepSegment<4?void 0:parseInt(e.milliseconds||0)),e}get __stepSegment(){if(this.step%3600===0)return 1;if(this.step%60===0||!this.step)return 2;if(this.step%1===0)return 3;if(this.step<1)return 4}__formatISO(e){return W.properties.i18n.value().formatTime(e)}__parseISO(e){return W.properties.i18n.value().parseTime(e)}_timeAllowed(e){const t=this.i18n.parseTime(this.min||fi),s=this.i18n.parseTime(this.max||_i);return(!this.__getMsec(t)||this.__getMsec(e)>=this.__getMsec(t))&&(!this.__getMsec(s)||this.__getMsec(e)<=this.__getMsec(s))}_onClearButtonClick(){}_onChange(){}_onInput(){this._checkInputValue()}}customElements.define(W.is,W);u("vaadin-date-time-picker",[Fe,Oe,Vs],{moduleId:"lumo-date-time-picker"});u("vaadin-date-time-picker-date-picker",p`
    :host {
      margin-right: 2px;
    }

    /* RTL specific styles */
    :host([dir='rtl']) {
      margin-right: auto;
      margin-left: 2px;
    }

    [part~='input-field'] {
      border-top-right-radius: 0;
      border-bottom-right-radius: 0;
    }

    /* RTL specific styles */
    :host([dir='rtl']) [part~='input-field'] {
      border-radius: var(--lumo-border-radius-m);
      border-top-left-radius: 0;
      border-bottom-left-radius: 0;
    }
  `,{moduleId:"lumo-date-time-picker-date-picker"});u("vaadin-date-time-picker-time-picker",p`
    [part~='input-field'] {
      border-top-left-radius: 0;
      border-bottom-left-radius: 0;
    }

    /* RTL specific styles */
    :host([dir='rtl']) [part~='input-field'] {
      border-radius: var(--lumo-border-radius-m);
      border-top-right-radius: 0;
      border-bottom-right-radius: 0;
    }
  `,{moduleId:"lumo-date-time-picker-time-picker"});/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class gi extends Pr{static get is(){return"vaadin-date-time-picker-date-picker"}}customElements.define(gi.is,gi);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class vi extends W{static get is(){return"vaadin-date-time-picker-time-picker"}}customElements.define(vi.is,vi);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-date-time-picker",gt,{moduleId:"vaadin-date-time-picker"});function $s(i,e){for(;i;){if(i.properties&&i.properties[e])return i.properties[e];i=Object.getPrototypeOf(i)}}const ot=customElements.get("vaadin-date-time-picker-date-picker"),Fa=customElements.get("vaadin-date-time-picker-time-picker"),Hs=$s(ot,"i18n").value(),Ae=$s(Fa,"i18n").value(),yi=Object.keys(Hs),bi=Object.keys(Ae);class Ai extends de(Ns(Re(O(y(A(g)))))){static get template(){return f`
      <style>
        .vaadin-date-time-picker-container {
          --vaadin-field-default-width: auto;
        }

        .slots {
          display: flex;
          --vaadin-field-default-width: 12em;
        }

        .slots ::slotted([slot='date-picker']) {
          min-width: 0;
          flex: 1 1 auto;
        }

        .slots ::slotted([slot='time-picker']) {
          min-width: 0;
          flex: 1 1.65 auto;
        }
      </style>

      <div class="vaadin-date-time-picker-container">
        <div part="label" on-click="focus">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true"></span>
        </div>

        <div class="slots">
          <slot name="date-picker" id="dateSlot"></slot>
          <slot name="time-picker" id="timeSlot"></slot>
        </div>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get is(){return"vaadin-date-time-picker"}static get properties(){return{name:{type:String},value:{type:String,notify:!0,value:"",observer:"__valueChanged"},min:{type:String,observer:"__minChanged"},max:{type:String,observer:"__maxChanged"},__minDateTime:{type:Date,value:""},__maxDateTime:{type:Date,value:""},datePlaceholder:{type:String},timePlaceholder:{type:String},step:{type:Number},initialPosition:String,showWeekNumbers:{type:Boolean},autoOpenDisabled:Boolean,readonly:{type:Boolean,value:!1,reflectToAttribute:!0},autofocus:{type:Boolean},__selectedDateTime:{type:Date},i18n:{type:Object,value:()=>({...Hs,...Ae})},__datePicker:{type:HTMLElement,observer:"__datePickerChanged"},__timePicker:{type:HTMLElement,observer:"__timePickerChanged"}}}static get observers(){return["__selectedDateTimeChanged(__selectedDateTime)","__datePlaceholderChanged(datePlaceholder)","__timePlaceholderChanged(timePlaceholder)","__stepChanged(step)","__initialPositionChanged(initialPosition)","__showWeekNumbersChanged(showWeekNumbers)","__requiredChanged(required)","__invalidChanged(invalid)","__disabledChanged(disabled)","__readonlyChanged(readonly)","__i18nChanged(i18n.*)","__autoOpenDisabledChanged(autoOpenDisabled)","__themeChanged(_theme, __datePicker, __timePicker)","__pickersChanged(__datePicker, __timePicker)"]}get slots(){return{...super.slots,"date-picker":()=>{const e=document.createElement("vaadin-date-time-picker-date-picker");return e.__defaultPicker=!0,e},"time-picker":()=>{const e=document.createElement("vaadin-date-time-picker-time-picker");return e.__defaultPicker=!0,e}}}constructor(){super(),this.__defaultDateMinMaxValue=void 0,this.__defaultTimeMinValue="00:00:00.000",this.__defaultTimeMaxValue="23:59:59.999",this.__changeEventHandler=this.__changeEventHandler.bind(this),this.__valueChangedEventHandler=this.__valueChangedEventHandler.bind(this),this._observer=new k(this,e=>{this.__onDomChange(e.addedNodes)})}ready(){super.ready(),this.__datePicker=this._getDirectSlotChild("date-picker"),this.__timePicker=this._getDirectSlotChild("time-picker"),this.autofocus&&!this.disabled&&window.requestAnimationFrame(()=>this.focus()),this.setAttribute("role","group"),this.ariaTarget=this}focus(){this.__datePicker.focus()}_setFocused(e){super._setFocused(e),e||this.validate()}_shouldRemoveFocus(e){const t=e.relatedTarget;return!(this.__datePicker.contains(t)||this.__timePicker.contains(t)||t===this.__datePicker.$.overlay)}__syncI18n(e,t,s){s=s||Object.keys(t.i18n),s.forEach(r=>{t.i18n&&t.i18n.hasOwnProperty(r)&&e.set(`i18n.${r}`,t.i18n[r])})}__changeEventHandler(e){e.stopPropagation(),this.__dispatchChangeForValue===this.value&&(this.__dispatchChange(),this.validate()),this.__dispatchChangeForValue=void 0}__addInputListeners(e){e.addEventListener("change",this.__changeEventHandler),e.addEventListener("value-changed",this.__valueChangedEventHandler)}__removeInputListeners(e){e.removeEventListener("change",this.__changeEventHandler),e.removeEventListener("value-changed",this.__valueChangedEventHandler)}__onDomChange(e){e.filter(t=>t.nodeType===Node.ELEMENT_NODE).forEach(t=>{const s=t.getAttribute("slot");s==="date-picker"?this.__datePicker=t:s==="time-picker"&&(this.__timePicker=t)}),this.value&&(this.min||this.max)&&this.validate()}__datePickerChanged(e,t){!e||(t&&(this.__removeInputListeners(t),t.remove()),this.__addInputListeners(e),e.__defaultPicker?(e.placeholder=this.datePlaceholder,e.invalid=this.invalid,e.initialPosition=this.initialPosition,e.showWeekNumbers=this.showWeekNumbers,this.__syncI18n(e,this,yi)):(this.datePlaceholder=e.placeholder,this.initialPosition=e.initialPosition,this.showWeekNumbers=e.showWeekNumbers,this.__syncI18n(this,e,yi)),e.min=this.__formatDateISO(this.__minDateTime,this.__defaultDateMinMaxValue),e.max=this.__formatDateISO(this.__maxDateTime,this.__defaultDateMinMaxValue),e.required=this.required,e.disabled=this.disabled,e.readonly=this.readonly,e.autoOpenDisabled=this.autoOpenDisabled,e.validate=()=>{},e._validateInput=()=>{})}__timePickerChanged(e,t){!e||(t&&(this.__removeInputListeners(t),t.remove()),this.__addInputListeners(e),e.__defaultPicker?(e.placeholder=this.timePlaceholder,e.step=this.step,e.invalid=this.invalid,this.__syncI18n(e,this,bi)):(this.timePlaceholder=e.placeholder,this.step=e.step,this.__syncI18n(this,e,bi)),this.__updateTimePickerMinMax(),e.required=this.required,e.disabled=this.disabled,e.readonly=this.readonly,e.autoOpenDisabled=this.autoOpenDisabled,e.validate=()=>{})}__updateTimePickerMinMax(){if(this.__timePicker&&this.__datePicker){const e=this.__parseDate(this.__datePicker.value),t=me(this.__minDateTime,this.__maxDateTime),s=this.__timePicker.value;this.__minDateTime&&me(e,this.__minDateTime)||t?this.__timePicker.min=this.__dateToIsoTimeString(this.__minDateTime):this.__timePicker.min=this.__defaultTimeMinValue,this.__maxDateTime&&me(e,this.__maxDateTime)||t?this.__timePicker.max=this.__dateToIsoTimeString(this.__maxDateTime):this.__timePicker.max=this.__defaultTimeMaxValue,this.__timePicker.value!==s&&(this.__timePicker.value=s)}}__i18nChanged(e){this.__datePicker&&this.__datePicker.set(e.path,e.value),this.__timePicker&&this.__timePicker.set(e.path,e.value)}__datePlaceholderChanged(e){this.__datePicker&&(this.__datePicker.placeholder=e)}__timePlaceholderChanged(e){this.__timePicker&&(this.__timePicker.placeholder=e)}__stepChanged(e){this.__timePicker&&this.__timePicker.step!==e&&(this.__timePicker.step=e)}__initialPositionChanged(e){this.__datePicker&&(this.__datePicker.initialPosition=e)}__showWeekNumbersChanged(e){this.__datePicker&&(this.__datePicker.showWeekNumbers=e)}__invalidChanged(e){this.__datePicker&&(this.__datePicker.invalid=e),this.__timePicker&&(this.__timePicker.invalid=e)}__requiredChanged(e){this.__datePicker&&(this.__datePicker.required=e),this.__timePicker&&(this.__timePicker.required=e)}__disabledChanged(e){this.__datePicker&&(this.__datePicker.disabled=e),this.__timePicker&&(this.__timePicker.disabled=e)}__readonlyChanged(e){this.__datePicker&&(this.__datePicker.readonly=e),this.__timePicker&&(this.__timePicker.readonly=e)}__parseDate(e){return ot.prototype._parseDate(e)}__formatDateISO(e,t){return e?ot.prototype._formatISO(e):t}__formatTimeISO(e){return Ae.formatTime(e)}__parseTimeISO(e){return Ae.parseTime(e)}__parseDateTime(e){const[t,s]=e.split("T");if(!(t&&s))return;const r=this.__parseDate(t);if(!r)return;const a=this.__parseTimeISO(s);if(!!a)return r.setHours(parseInt(a.hours)),r.setMinutes(parseInt(a.minutes||0)),r.setSeconds(parseInt(a.seconds||0)),r.setMilliseconds(parseInt(a.milliseconds||0)),r}__formatDateTime(e){if(!e)return"";const t=this.__formatDateISO(e,""),s=this.__dateToIsoTimeString(e);return`${t}T${s}`}__dateToIsoTimeString(e){return this.__formatTimeISO(this.__validateTime({hours:e.getHours(),minutes:e.getMinutes(),seconds:e.getSeconds(),milliseconds:e.getMilliseconds()}))}__validateTime(e){return e&&(e.seconds=this.__stepSegment<3?void 0:e.seconds,e.milliseconds=this.__stepSegment<4?void 0:e.milliseconds),e}get __inputs(){return[this.__datePicker,this.__timePicker]}checkValidity(){const e=this.__inputs.some(s=>!s.checkValidity()),t=this.required&&this.__inputs.some(s=>!s.value);return!(e||t)}get __stepSegment(){const e=this.step==null?60:parseFloat(this.step);if(e%3600===0)return 1;if(e%60===0||!e)return 2;if(e%1===0)return 3;if(e<1)return 4}__dateTimeEquals(e,t){return me(e,t)?e.getHours()===t.getHours()&&e.getMinutes()===t.getMinutes()&&e.getSeconds()===t.getSeconds()&&e.getMilliseconds()===t.getMilliseconds():!1}__handleDateTimeChange(e,t,s,r){if(!s){this[e]="",this[t]="";return}const a=this.__parseDateTime(s);if(!a){this[e]=r;return}this.__dateTimeEquals(this[t],a)||(this[t]=a)}__valueChanged(e,t){this.__handleDateTimeChange("value","__selectedDateTime",e,t),t!==void 0&&(this.__dispatchChangeForValue=e),this.toggleAttribute("has-value",!!e),this.__updateTimePickerMinMax()}__dispatchChange(){this.dispatchEvent(new CustomEvent("change",{bubbles:!0}))}__minChanged(e,t){this.__handleDateTimeChange("min","__minDateTime",e,t),this.__datePicker&&(this.__datePicker.min=this.__formatDateISO(this.__minDateTime,this.__defaultDateMinMaxValue)),this.__updateTimePickerMinMax(),this.__datePicker&&this.__timePicker&&this.value&&this.validate()}__maxChanged(e,t){this.__handleDateTimeChange("max","__maxDateTime",e,t),this.__datePicker&&(this.__datePicker.max=this.__formatDateISO(this.__maxDateTime,this.__defaultDateMinMaxValue)),this.__updateTimePickerMinMax(),this.__datePicker&&this.__timePicker&&this.value&&this.validate()}__selectedDateTimeChanged(e){const t=this.__formatDateTime(e);if(this.value!==t&&(this.value=t),Boolean(this.__datePicker&&this.__datePicker.$)&&!this.__ignoreInputValueChange){this.__ignoreInputValueChange=!0;const[r,a]=this.value.split("T");this.__datePicker.value=r||"",this.__timePicker.value=a||"",this.__ignoreInputValueChange=!1}}get __formattedValue(){const e=this.__datePicker.value,t=this.__timePicker.value;return e&&t?[e,t].join("T"):""}__valueChangedEventHandler(){if(this.__ignoreInputValueChange)return;const e=this.__formattedValue,[t,s]=e.split("T");this.__ignoreInputValueChange=!0,this.__updateTimePickerMinMax(),t&&s?e!==this.value&&(this.value=e):this.value="",this.__ignoreInputValueChange=!1}__autoOpenDisabledChanged(e){this.__datePicker&&(this.__datePicker.autoOpenDisabled=e),this.__timePicker&&(this.__timePicker.autoOpenDisabled=e)}__themeChanged(e,t,s){!t||!s||[t,s].forEach(r=>{e?r.setAttribute("theme",e):r.removeAttribute("theme")})}__pickersChanged(e,t){!e||!t||e.__defaultPicker===t.__defaultPicker&&(e.value?this.__valueChangedEventHandler():this.value&&this.__selectedDateTimeChanged(this.__selectedDateTime))}}customElements.define(Ai.is,Ai);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Oa=p`
  :host([dir='rtl']) [part='input-field'] ::slotted(input) {
    --_lumo-text-field-overflow-mask-image: linear-gradient(to left, transparent, #000 1.25em);
  }

  :host([dir='rtl']) [part='input-field'] ::slotted(input:placeholder-shown) {
    --_lumo-text-field-overflow-mask-image: none;
  }
`;u("vaadin-email-field",[De,Oa],{moduleId:"lumo-email-field"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-email-field",p`
    :host([dir='rtl']) [part='input-field'] {
      direction: ltr;
    }

    :host([dir='rtl']) [part='input-field'] ::slotted(input)::placeholder {
      direction: rtl;
      text-align: left;
    }
  `,{moduleId:"vaadin-email-field-styles"});class Ra extends Br{static get is(){return"vaadin-email-field"}constructor(){super(),this._setType("email"),this.pattern="^([a-zA-Z0-9_\\.\\-+])+@[a-zA-Z0-9-.]+\\.[a-zA-Z0-9-]{2,}$"}ready(){super.ready(),this.inputElement&&(this.inputElement.autocapitalize="off")}}customElements.define("vaadin-email-field",Ra);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-field-outline",p`
    :host {
      transition: opacity 0.3s;
      -webkit-mask-image: none !important;
      mask-image: none !important;
    }

    :host::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      box-shadow: 0 0 0 2px var(--_active-user-color);
      border-radius: var(--lumo-border-radius);
      transition: box-shadow 0.3s;
    }

    :host([context$='checkbox'])::before {
      box-shadow: 0 0 0 2px var(--lumo-base-color), 0 0 0 4px var(--_active-user-color);
    }

    :host([context$='radio-button'])::before {
      border-radius: 50%;
      box-shadow: 0 0 0 3px var(--lumo-base-color), 0 0 0 5px var(--_active-user-color);
    }

    :host([context$='item'])::before {
      box-shadow: inset 0 0 0 2px var(--_active-user-color);
    }
  `,{moduleId:"lumo-field-outline"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-user-tags-overlay",p`
    [part='overlay'] {
      will-change: opacity, transform;
    }

    :host([opening]) [part='overlay'] {
      animation: 0.1s lumo-user-tags-enter ease-out both;
    }

    @keyframes lumo-user-tags-enter {
      0% {
        opacity: 0;
      }
    }

    :host([closing]) [part='overlay'] {
      animation: 0.1s lumo-user-tags-exit both;
    }

    @keyframes lumo-user-tags-exit {
      100% {
        opacity: 0;
      }
    }
  `,{moduleId:"lumo-user-tags-overlay"});u("vaadin-user-tag",p`
    :host {
      font-family: var(--lumo-font-family);
      font-size: var(--lumo-font-size-xxs);
      border-radius: var(--lumo-border-radius-s);
      box-shadow: var(--lumo-box-shadow-xs);
      --vaadin-user-tag-offset: var(--lumo-space-xs);
    }

    [part='name'] {
      color: var(--lumo-primary-contrast-color);
      padding: 0.3em calc(0.3em + var(--lumo-border-radius-s) / 4);
      line-height: 1;
      font-weight: 500;
      min-width: calc(var(--lumo-line-height-xs) * 1em + 0.45em);
    }
  `,{moduleId:"lumo-user-tag"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class xi extends y(vt(g)){static get is(){return"vaadin-user-tag"}static get template(){return f`
      <style>
        :host {
          display: block;
          box-sizing: border-box;
          margin: 0 0 var(--vaadin-user-tag-offset);
          opacity: 0;
          height: 1.3rem;
          transition: opacity 0.2s ease-in-out;
          background-color: var(--vaadin-user-tag-color);
          color: #fff;
          cursor: default;
          -webkit-user-select: none;
          user-select: none;
          --vaadin-user-tag-offset: 4px;
        }

        :host(.show) {
          opacity: 1;
        }

        :host(:last-of-type) {
          margin-bottom: 0;
        }

        [part='name'] {
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
          box-sizing: border-box;
          padding: 2px 4px;
          height: 1.3rem;
          font-size: 13px;
        }
      </style>
      <div part="name">[[name]]</div>
    `}static get properties(){return{name:{type:String},uid:{type:String},colorIndex:{type:Number,observer:"_colorIndexChanged"}}}ready(){super.ready(),this.addEventListener("mousedown",this._onClick.bind(this),!0)}_colorIndexChanged(e){e!=null&&this.style.setProperty("--vaadin-user-tag-color",`var(--vaadin-user-color-${e})`)}_onClick(e){e.preventDefault(),this.dispatchEvent(new CustomEvent("user-tag-click",{bubbles:!0,composed:!0,detail:{name:this.name}}))}}customElements.define(xi.is,xi);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-user-tags-overlay",p`
    :host {
      background: transparent;
      box-shadow: none;
    }

    [part='overlay'] {
      box-shadow: none;
      background: transparent;
      position: relative;
      left: -4px;
      padding: 4px;
      outline: none;
      overflow: visible;
    }

    ::slotted([part='tags']) {
      display: flex;
      flex-direction: column;
      align-items: flex-start;
    }

    :host([dir='rtl']) [part='overlay'] {
      left: auto;
      right: -4px;
    }

    [part='content'] {
      padding: 0;
    }

    :host([opening]),
    :host([closing]) {
      animation: 0.14s user-tags-overlay-dummy-animation;
    }

    @keyframes user-tags-overlay-dummy-animation {
      0% {
        opacity: 1;
      }

      100% {
        opacity: 1;
      }
    }
  `);class wi extends mt(Me){static get is(){return"vaadin-user-tags-overlay"}ready(){super.ready(),this.$.overlay.setAttribute("tabindex","-1")}}customElements.define(wi.is,wi);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ci=(i,e)=>new Promise(t=>{const s=()=>{i.removeEventListener(e,s),t()};i.addEventListener(e,s)});class Ei extends g{static get is(){return"vaadin-user-tags"}static get template(){return f`
      <style>
        :host {
          position: absolute;
        }
      </style>
      <vaadin-user-tags-overlay
        id="overlay"
        modeless
        opened="[[opened]]"
        no-vertical-overlap
        on-vaadin-overlay-open="_onOverlayOpen"
      ></vaadin-user-tags-overlay>
    `}static get properties(){return{hasFocus:{type:Boolean,value:!1,observer:"_hasFocusChanged"},opened:{type:Boolean,value:!1},flashing:{type:Boolean,value:!1},target:{type:Object,observer:"__targetChanged"},users:{type:Array,value:()=>[]},duration:{type:Number,value:200},delay:{type:Number,value:2e3},__flashQueue:{type:Array,value:()=>[]},__isTargetVisible:{type:Boolean,value:!1}}}constructor(){super(),this.__targetVisibilityObserver=new IntersectionObserver(([e])=>{this.__onTargetVisibilityChange(e.isIntersecting)},{threshold:1})}connectedCallback(){super.connectedCallback(),this.target&&this.__targetVisibilityObserver.observe(this.target)}disconnectedCallback(){super.disconnectedCallback(),this.opened=!1,this.target&&this.__targetVisibilityObserver.unobserve(this.target)}ready(){super.ready(),this.$.overlay.renderer=e=>{if(!e.firstChild){const t=document.createElement("div");t.setAttribute("part","tags"),e.appendChild(t)}},this.$.overlay.requestContentUpdate()}__onTargetVisibilityChange(e){if(this.__isTargetVisible=e,e&&this.__flashQueue.length>0&&!this.flashing){this.flashTags(this.__flashQueue.shift());return}if(e&&this.hasFocus){this.opened=!0;return}!e&&this.opened&&(this.opened=!1)}__targetChanged(e,t){this.$.overlay.positionTarget=e,t&&this.__targetVisibilityObserver.unobserve(t),e&&this.__targetVisibilityObserver.observe(e)}_hasFocusChanged(e){e&&this.flashing&&this.stopFlash()}get wrapper(){return this.$.overlay.content.querySelector('[part="tags"]')}createUserTag(e){const t=document.createElement("vaadin-user-tag");return t.name=e.name,t.uid=e.id,t.colorIndex=e.colorIndex,t}getTagForUser(e){return Array.from(this.wrapper.children).filter(t=>t.uid===e.id)[0]}getChangedTags(e,t){const s=t.map(a=>this.getTagForUser(a));return{added:e.map(a=>this.getTagForUser(a)||this.createUserTag(a)),removed:s}}getChangedUsers(e,t){const s=[],r=[];t.forEach(n=>{for(let o=0;o<n.removed.length;o++)r.push(n.removed[o]);for(let o=n.addedCount-1;o>=0;o--)s.push(e[n.index+o])});const a=s.filter(n=>!r.some(o=>n.id===o.id)),l=r.filter(n=>!s.some(o=>n.id===o.id));return{addedUsers:a,removedUsers:l}}applyTagsStart({added:e,removed:t}){const s=this.wrapper;t.forEach(r=>{r&&(r.classList.add("removing"),r.classList.remove("show"))}),e.forEach(r=>s.insertBefore(r,s.firstChild))}applyTagsEnd({added:e,removed:t}){const s=this.wrapper;t.forEach(r=>{r&&r.parentNode===s&&s.removeChild(r)}),e.forEach(r=>r&&r.classList.add("show"))}setUsers(e){this.requestContentUpdate();const t=ht(e,this.users);if(t.length===0)return;const{addedUsers:s,removedUsers:r}=this.getChangedUsers(e,t);if(s.length===0&&r.length===0)return;const a=this.getChangedTags(s,r);if(this.__flashQueue.length>0){for(let l=0;l<r.length;l++)if(a.removed[l]===null)for(let n=0;n<this.__flashQueue.length;n++)this.__flashQueue[n].some(o=>o.uid===r[l].id)&&this.splice("__flashQueue",l,1)}if(this.opened&&this.hasFocus)this.updateTags(e,a);else if(s.length>0&&document.visibilityState!=="hidden"){const l=a.added,n=a.removed;this.updateTagsSync(e,{added:[],removed:n}),this.flashing||!this.__isTargetVisible?this.push("__flashQueue",l):this.flashTags(l)}else this.updateTagsSync(e,a)}_onOverlayOpen(){Array.from(this.wrapper.children).forEach(e=>{e.classList.contains("removing")||e.classList.add("show")})}flashTags(e){this.flashing=!0;const t=this.wrapper,s=Array.from(t.children);s.forEach(r=>{r.style.display="none"}),e.forEach(r=>{t.insertBefore(r,t.firstChild)}),this.flashPromise=new Promise(r=>{Ci(this.$.overlay,"vaadin-overlay-open").then(()=>{this._debounceFlashStart=U.debounce(this._debounceFlashStart,te.after(this.duration+this.delay),()=>{this.hasFocus||e.forEach(a=>a.classList.remove("show")),this._debounceFlashEnd=U.debounce(this._debounceFlashEnd,te.after(this.duration),()=>{const a=()=>{s.forEach(l=>{l.style.display="block"}),this.flashing=!1,r()};this.hasFocus?a():(Ci(this.$.overlay,"animationend").then(()=>{a()}),this.opened=!1)})})})}).then(()=>{if(this.__flashQueue.length>0){const r=this.__flashQueue[0];this.splice("__flashQueue",0,1),this.flashTags(r)}}),this.opened=!0}stopFlash(){this._debounceFlashStart&&this._debounceFlashStart.flush(),this._debounceFlashEnd&&this._debounceFlashEnd.flush(),this.$.overlay._flushAnimation("closing")}updateTags(e,t){this.applyTagsStart(t),this._debounceRender=U.debounce(this._debounceRender,te.after(this.duration),()=>{this.set("users",e),this.applyTagsEnd(t),e.length===0&&this.opened&&(this.opened=!1)})}updateTagsSync(e,t){this.applyTagsStart(t),this.set("users",e),this.applyTagsEnd(t)}show(){this.hasFocus=!0,this.__isTargetVisible&&(this.opened=!0)}hide(){this.hasFocus=!1,this.opened=!1}requestContentUpdate(){this._debounceRender&&this._debounceRender.isActive()&&this._debounceRender.flush()}}customElements.define(Ei.is,Ei);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Si extends y(vt(g)){static get is(){return"vaadin-field-outline"}static get template(){return f`
      <style>
        :host {
          display: block;
          box-sizing: border-box;
          position: absolute;
          top: 0;
          left: 0;
          right: 0;
          bottom: 0;
          width: 100%;
          height: 100%;
          pointer-events: none;
          user-select: none;
          opacity: 0;
          --_active-user-color: transparent;
        }

        :host([has-active-user]) {
          opacity: 1;
        }
      </style>
    `}static get properties(){return{user:{type:Object,value:null,observer:"_userChanged"}}}ready(){super.ready(),this.setAttribute("part","outline"),this._field=this.getRootNode().host}_userChanged(e){this.toggleAttribute("has-active-user",Boolean(e));const t=e?`var(--vaadin-user-color-${e.colorIndex})`:"transparent",s="--_active-user-color";this.style.setProperty(s,t),this._field&&this._field.style.setProperty(s,t)}}customElements.define(Si.is,Si);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Da=(i,e)=>{switch(e){case"vaadin-big-decimal-field":case"vaadin-combo-box":case"vaadin-date-picker":case"vaadin-date-time-picker-date-picker":case"vaadin-date-time-picker-time-picker":case"vaadin-email-field":case"vaadin-integer-field":case"vaadin-number-field":case"vaadin-password-field":case"vaadin-select":case"vaadin-text-area":case"vaadin-text-field":case"vaadin-time-picker":return i.shadowRoot.querySelector('[part="input-field"]');case"vaadin-checkbox":return i.shadowRoot.querySelector('[part="checkbox"]');case"vaadin-radio-button":return i.shadowRoot.querySelector('[part="radio"]');default:return i}},We=new WeakMap,La=i=>{if(!We.has(i)){const e=i.tagName.toLowerCase(),t=Da(i,e);t.style.position="relative",e.endsWith("text-area")&&(t.style.overflow="visible");const s=document.createElement("style");s.textContent=`
      :host([active]) [part="outline"],
      :host([focus-ring]) [part="outline"] {
        display: none;
      }
    `,i.shadowRoot.appendChild(s);const r=document.createElement("vaadin-field-outline");(t===i?i.shadowRoot:t).appendChild(r),r.setAttribute("context",e),We.set(i,{root:i,target:t,outline:r})}return We.get(i)};/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class It{constructor(e){this.component=e,this.initTags(e)}getFields(){return[this.component]}getFieldIndex(e){return this.getFields().indexOf(e)}getFocusTarget(e){return this.component}initTags(e){const t=document.createElement("vaadin-user-tags");e.shadowRoot.appendChild(t),t.target=e,this._tags=t,e.addEventListener("mouseenter",s=>{s.relatedTarget!==this._tags.$.overlay&&(this._mouse=!0,this._mouseDebouncer=U.debounce(this._mouseDebouncer,te.after(200),()=>{this._mouse&&this._tags.show()}))}),e.addEventListener("mouseleave",s=>{s.relatedTarget!==this._tags.$.overlay&&(this._mouse=!1,this._hasFocus||this._tags.hide())}),e.addEventListener("vaadin-highlight-show",s=>{this._hasFocus=!0,this._debouncer&&this._debouncer.isActive()?this._debouncer.cancel():this._tags.show()}),e.addEventListener("vaadin-highlight-hide",s=>{this._hasFocus=!1,this._mouse||(this._debouncer=U.debounce(this._debouncer,te.after(1),()=>{this._tags.hide()}))}),this._tags.$.overlay.addEventListener("mouseleave",s=>{s.relatedTarget!==e&&(this._mouse=!1,e.hasAttribute("focused")||this._tags.hide())})}setOutlines(e){const t=this.getFields();t.forEach((s,r)=>{const{outline:a}=La(s),l=t.length===1?0:e.map(n=>n.fieldIndex).indexOf(r);a.user=e[l]})}showOutline(e){this.fire("show",e)}hideOutline(e){this.fire("hide",e)}fire(e,t){this.component.dispatchEvent(new CustomEvent(`vaadin-highlight-${e}`,{bubbles:!0,composed:!0,detail:{fieldIndex:this.getFieldIndex(t)}}))}redraw(e){this._tags.setUsers(e),this.setOutlines(e)}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Y extends It{constructor(e){super(e),this.addListeners(e)}addListeners(e){e.addEventListener("focusin",t=>this.onFocusIn(t)),e.addEventListener("focusout",t=>this.onFocusOut(t))}onFocusIn(e){const t=this.getFocusTarget(e);this.showOutline(t)}onFocusOut(e){const t=this.getFocusTarget(e);this.hideOutline(t)}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class za extends Y{getFields(){return this.component.__checkboxes}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(s=>t.includes(s))[0]}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Us extends It{constructor(e){super(e),this.datePicker=e,this.fullscreenFocus=!1,this.blurWhileOpened=!1,this.addListeners(e)}addListeners(e){this.overlay=e.$.overlay,e.addEventListener("blur",t=>this.onBlur(t),!0),e.addEventListener("opened-changed",t=>this.onOpenedChanged(t)),this.overlay.addEventListener("focusout",t=>this.onOverlayFocusOut(t)),e.addEventListener("focusin",t=>this.onFocusIn(t)),e.addEventListener("focusout",t=>this.onFocusOut(t))}onBlur(e){this.datePicker._fullscreen&&e.relatedTarget!==this.overlay&&(this.fullscreenFocus=!0)}onFocusIn(e){if(e.relatedTarget!==this.overlay){if(this.blurWhileOpened){this.blurWhileOpened=!1;return}this.showOutline(this.datePicker)}}onFocusOut(e){this.fullscreenFocus||e.relatedTarget===this.overlay||(this.datePicker.opened?this.blurWhileOpened=!0:this.hideOutline(this.datePicker))}onOverlayFocusOut(e){this.datePicker.contains(e.relatedTarget)||(this.blurWhileOpened=!0)}onOpenedChanged(e){e.detail.value===!0&&this.fullscreenFocus&&(this.fullscreenFocus=!1,this.showOutline(this.datePicker)),e.detail.value===!1&&this.blurWhileOpened&&(this.blurWhileOpened=!1,this.hideOutline(this.datePicker))}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Na extends Us{constructor(e,t){super(e),this.component=t}getFieldIndex(){return 0}}class Va extends Y{constructor(e,t){super(e),this.component=t,this.timePicker=e}getFocusTarget(e){return this.timePicker}getFieldIndex(){return 1}}class $a extends It{constructor(e){super(e);const[t,s]=this.getFields();this.dateObserver=new Na(t,e),this.timeObserver=new Va(s,e)}getFields(){return this.component.__inputs}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Ha extends Y{getFields(){return this.component.items||[]}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(s=>t.includes(s))[0]}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Ua extends Y{getFields(){return this.component.__radioButtons}getFocusTarget(e){const t=this.getFields();return Array.from(e.composedPath()).filter(s=>t.includes(s))[0]}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class ja extends Y{constructor(e){super(e),this.blurWhileOpened=!1,this.overlay=e._overlayElement}addListeners(e){super.addListeners(e),e.addEventListener("opened-changed",t=>{e._phone&&t.detail.value===!1&&this.hideOutline(e)})}onFocusIn(e){this.overlay.contains(e.relatedTarget)||!this.component._phone&&this.overlay.hasAttribute("closing")||super.onFocusIn(e)}onFocusOut(e){this.overlay.contains(e.relatedTarget)||super.onFocusOut(e)}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ga=i=>{let e;switch(i.tagName.toLowerCase()){case"vaadin-date-picker":e=new Us(i);break;case"vaadin-date-time-picker":e=new $a(i);break;case"vaadin-select":e=new ja(i);break;case"vaadin-checkbox-group":e=new za(i);break;case"vaadin-radio-group":e=new Ua(i);break;case"vaadin-list-box":e=new Ha(i);break;default:e=new Y(i)}return e};class Wa{get user(){return this._user}set user(e){if(this._user=e,e){const t=`${e.name} started editing`,{label:s}=this.host;V(s?`${t} ${s}`:t)}}constructor(e){this.host=e,this.user=null,this.users=[]}hostConnected(){this.redraw()}addUser(e){e&&(this.users.push(e),this.redraw(),this.user=e)}setUsers(e){Array.isArray(e)&&(this.users=e,this.redraw(),this.user=e[e.length-1]||null)}removeUser(e){if(e&&e.id!==void 0){let t;for(let s=0;s<this.users.length;s++)if(this.users[s].id===e.id){t=s;break}t!==void 0&&(this.users.splice(t,1),this.redraw(),this.users.length>0?this.user=this.users[this.users.length-1]:this.user=null)}}redraw(){this.observer.redraw([...this.users].reverse())}}class Ya extends HTMLElement{static init(e){if(!e._highlighterController){const t=new Wa(e);e.setAttribute("has-highlighter",""),t.observer=Ga(e),e.addController(t),e._highlighterController=t}return e._highlighterController}static addUser(e,t){this.init(e).addUser(t)}static removeUser(e,t){this.init(e).removeUser(t)}static setUsers(e,t){this.init(e).setUsers(t)}}customElements.define("vaadin-field-highlighter",Ya);(function(){function i(t){const s=t._overlayElement;s&&(s.className=t.className)}const e=new MutationObserver(t=>{t.forEach(s=>{s.type==="attributes"&&s.attributeName==="class"&&i(s.target)})});window.Vaadin.Flow.confirmDialogConnector={initLazy:function(t){t.$connector||(t.$connector={},t.addEventListener("opened-changed",s=>{s.detail.value&&i(t)}),e.observe(t,{attributes:!0,attributeFilter:["class"]}),i(t))}}})();window.Vaadin.Flow.ironListConnector={initLazy:function(i){if(i.$connector)return;const e=20;let t=[0,0];i.$connector={},i.$connector.placeholderItem={__placeholder:!0};const s=function(){let n=i._virtualStart,o=i._virtualEnd,d=Math.max(0,n-e),h=Math.min(o+e,i.items.length);if(t[0]!=d||t[1]!=h){t=[d,h];const c=1+h-d;i.$server.setRequestedRange(d,c)}};let r;const a=function(){r=oe.debounce(r,$.after(10),s)},l=i._assignModels;i._assignModels=function(){const n=[],o=i._virtualStart,d=Math.min(i.items.length,i._physicalCount);for(let h=0;h<d;h++)i.items[o+h]===void 0&&(n.push(h),i.items[o+h]=i.$connector.placeholderItem);l.apply(i,arguments);for(let h=0;h<n.length;h++)delete i.items[o+n[h]];a()},i.items=[],i.$connector.set=function(n,o){for(let d=0;d<o.length;d++){const h=n+d;i.items[h]=o[d]}i._render()},i.$connector.updateData=function(n){const o=i.items,d={};let h=n.length;for(let c=0;c<n.length;c++){const m=n[c];d[m.key]=m}for(let c=0;c<o.length;c++){const m=o[c],_=d[m.key];if(_&&(i.items[c]=_,i.notifyPath("items."+c),h--,h==0))break}},i.$connector.clear=function(n,o){for(let d=0;d<o;d++){const h=n+d;delete i.items[h],i.notifyPath("items."+h)}},i.$connector.updateSize=function(n){const o=n-i.items.length;if(o>0)i.items.length=n,i.notifySplices("items",[{index:n-o,removed:[],addedCount:o,object:i.items,type:"splice"}]);else if(o<0){const d=i.items.slice(n,i.items.length);i.items.splice(n),i.notifySplices("items",[{index:n,removed:d,addedCount:0,object:i.items,type:"splice"}])}},i.$connector.setPlaceholderItem=function(n){n||(n={}),n.__placeholder=!0,i.$connector.placeholderItem=n}}};const js=document.createElement("template");js.innerHTML=`<style>
/* Fixes zero width in flex layouts */
iron-list {
  flex: auto;
  align-self: stretch;
}
</style>`;document.head.appendChild(js.content);(function(){function i(t){const s=t.$.vaadinLoginOverlayWrapper;s&&(s.className=t.className)}const e=new MutationObserver(t=>{t.forEach(s=>{s.type==="attributes"&&s.attributeName==="class"&&i(s.target)})});window.Vaadin.Flow.loginOverlayConnector={initLazy:function(t){t.$connector||(t.$connector={},t.addEventListener("opened-changed",s=>{s.detail.value&&i(t)}),e.observe(t,{attributes:!0,attributeFilter:["class"]}),i(t))}}})();(function(){const i=function(t){return window.Vaadin.Flow.tryCatchWrapper(t,"Vaadin Menu Bar")};function e(t,s){if(t.$connector)return;const r=new MutationObserver(a=>{a.some(n=>{const o=n.oldValue,d=n.target.getAttribute(n.attributeName);return o!==d})&&t.$connector.generateItems()});t.$connector={generateItems:i(a=>{if(!t.shadowRoot){setTimeout(()=>t.$connector.generateItems(a));return}a&&(t.__generatedItems=window.Vaadin.Flow.contextMenuConnector.generateItemsTree(s,a));let l=t.__generatedItems||[];l.forEach(n=>n.disabled=n.component.disabled),l=l.filter(n=>!n.component.hidden),l.forEach(n=>{r.observe(n.component,{attributeFilter:["hidden","disabled"],attributeOldValue:!0})}),t.items=l,t._buttons.forEach(n=>{n.item&&n.item.component&&n.addEventListener("click",o=>{o.composedPath().indexOf(n.item.component)===-1&&(n.item.component.click(),o.stopPropagation())})})})}}window.Vaadin.Flow.menubarConnector={initLazy(...t){return i(e)(...t)}}})();(function(){const i=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Message List")};window.Vaadin.Flow.messageListConnector={setItems:(e,t,s)=>i(function(r,a,l){const n=new Intl.DateTimeFormat(l,{year:"numeric",month:"short",day:"numeric",hour:"numeric",minute:"numeric"});r.items=a.map(o=>o.time?Object.assign(o,{time:n.format(new Date(o.time))}):o)})(e,t,s)}})();(function(){const i=function(e){return window.Vaadin.Flow.tryCatchWrapper(e,"Vaadin Select")};window.Vaadin.Flow.selectConnector={initLazy:e=>i(function(t){const s=i(function(){for(let r=0;r<t.childElementCount;r++){const a=t.children[r];if(a.tagName.toUpperCase()==="VAADIN-SELECT-LIST-BOX")return a}});t.$connector||(t.$connector={},t.renderer=i(function(r){const a=s();a&&(r.firstChild&&r.removeChild(r.firstChild),r.appendChild(a))}))})(e)}})();(function(){let i;customElements.whenDefined("vaadin-text-field").then(()=>{class e extends customElements.get("vaadin-text-field"){static get template(){return i||(i=super.template.cloneNode(!0),i.innerHTML+=`<style>
                  :host {
                    width: 8em;
                  }

                  :host([dir="rtl"]) [part="input-field"] {
                    direction: ltr;
                  }

                  :host([dir="rtl"]) [part="input-field"] ::slotted(input) {
                    --_lumo-text-field-overflow-mask-image: linear-gradient(to left, transparent, #000 1.25em) !important;
                  }
            </style>`),i}static get is(){return"vaadin-big-decimal-field"}static get properties(){return{_decimalSeparator:{type:String,value:".",observer:"__decimalSeparatorChanged"}}}ready(){super.ready(),this.inputElement.setAttribute("inputmode","decimal")}__decimalSeparatorChanged(s,r){this.allowedCharPattern="[\\d-+"+s+"]",this.value&&r&&(this.value=this.value.split(r).join(s))}}customElements.define(e.is,e)})})();const qa={"\\u0660":"0","\\u0661":"1","\\u0662":"2","\\u0663":"3","\\u0664":"4","\\u0665":"5","\\u0666":"6","\\u0667":"7","\\u0668":"8","\\u0669":"9"};function Qa(i){return i.replace(/[.*+?^${}()|[\]\\]/g,"\\$&")}function Gs(i){return i.replace(/[\u0660-\u0669]/g,function(e){const t="\\u0"+e.charCodeAt(0).toString(16);return qa[t]})}function Ws(i,e){const t=e.toLocaleTimeString(i),s=/[^\d\u0660-\u0669]/,r=t.match(new RegExp(`${s.source}+$`,"g"))||t.match(new RegExp(`^${s.source}+`,"g"));return r&&r[0].trim()}function Ja(i){let e=kt.toLocaleTimeString(i);const t=Ys(i);t&&e.startsWith(t)&&(e=e.replace(t,""));const s=e.match(/[^\u0660-\u0669\s\d]/);return s&&s[0]}function Ii(i,e){if(!e)return null;const t=e.split(/\s*/).map(Qa).join("\\s*"),s=new RegExp(t,"i"),r=i.match(s);if(r)return r[0]}const kt=new Date("August 19, 1975 23:15:30"),Ka=new Date("August 19, 1975 05:15:30");function Ys(i){return Ws(i,kt)}function Za(i){return Ws(i,Ka)}function Ye(i){return parseInt(Gs(i))}function Xa(i){return i=Gs(i),i.length===1?i+="00":i.length===2&&(i+="0"),parseInt(i)}function eo(i,e,t,s){let r=i;if(i.endsWith(t)?r=i.replace(" "+t,""):i.endsWith(s)&&(r=i.replace(" "+s,"")),e){let a=e<10?"0":"";a+=e<100?"0":"",a+=e,r+="."+a}else r+=".000";return i.endsWith(t)?r=r+" "+t:i.endsWith(s)&&(r=r+" "+s),r}(function(){const i=function(t){return window.Vaadin.Flow.tryCatchWrapper(t,"Vaadin Time Picker")};function e(t,s,r=0){t()?s():setTimeout(()=>e(t,s,200),r)}window.Vaadin.Flow.timepickerConnector={initLazy:t=>i(function(s){s.$connector||(s.$connector={},s.$connector.setLocale=i(function(r){let a;s.value&&s.value!==""&&(a=s.i18n.parseTime(s.value));try{kt.toLocaleTimeString(r)}catch{throw r="en-US",new Error("vaadin-time-picker: The locale "+r+" is not supported, falling back to default locale setting(en-US).")}const l=Ys(r),n=Za(r),o=Ja(r),d=function(){return s.step&&s.step<60},h=function(){return s.step&&s.step<1};let c,m;s.i18n={formatTime:i(function(_){if(!_)return;const E=new Date;E.setHours(_.hours),E.setMinutes(_.minutes),E.setSeconds(_.seconds!==void 0?_.seconds:0);let I=E.toLocaleTimeString(r,{hour:"numeric",minute:"numeric",second:d()?"numeric":void 0});return h()&&(I=eo(I,_.milliseconds,n,l)),I}),parseTime:i(function(_){if(_&&_===c&&m)return m;if(!_)return;const E=Ii(_,n),I=Ii(_,l),q=_.replace(E||"","").replace(I||"","").trim(),R=new RegExp("([\\d\\u0660-\\u0669]){1,2}(?:"+o+")?","g");let T=R.exec(q);if(T){T=Ye(T[0].replace(o,"")),E!==I&&(T===12&&E&&(T=0),T!==12&&I&&(T+=12));const ue=R.exec(q),Q=ue&&R.exec(q),Zs=/[[\.][\d\u0660-\u0669]{1,3}$/;let J=Q&&h()&&Zs.exec(q);return J&&J.index<=Q.index&&(J=void 0),m=T!==void 0&&{hours:T,minutes:ue?Ye(ue[0].replace(o,"")):0,seconds:Q?Ye(Q[0].replace(o,"")):0,milliseconds:ue&&Q&&J?Xa(J[0].replace(".","")):0},c=_,m}})},a&&e(()=>s.$,()=>{const _=s.i18n.formatTime(a);s.inputElement.value!==_&&(s.inputElement.value=_,s.$.comboBox.value=_)})}))})(t)}})();window.Vaadin.Flow.virtualListConnector={initLazy:function(i){if(i.$connector)return;const e=20;let t=[0,0];i.$connector={},i.$connector.placeholderItem={__placeholder:!0};const s=function(){const a=[...i.children].filter(h=>"__virtualListIndex"in h).map(h=>h.__virtualListIndex),l=Math.min(...a),n=Math.max(...a);let o=Math.max(0,l-e),d=Math.min(n+e,i.items.length);if(t[0]!=o||t[1]!=d){t=[o,d];const h=1+d-o;i.$server.setRequestedRange(o,h)}},r=function(){i.__requestDebounce=oe.debounce(i.__requestDebounce,$.after(50),s)};requestAnimationFrame(()=>s),i.patchVirtualListRenderer=function(){if(!i.renderer||i.renderer.__virtualListConnectorPatched)return;const a=i.renderer,l=(n,o,d)=>{n.__virtualListIndex=d.index,d.item===void 0?a.call(o,n,o,{...d,item:o.$connector.placeholderItem}):a.call(o,n,o,d),r()};l.__virtualListConnectorPatched=!0,l.__rendererId=a.__rendererId,i.renderer=l},i._createPropertyObserver("renderer","patchVirtualListRenderer",!0),i.patchVirtualListRenderer(),i.items=[],i.$connector.set=function(a,l){i.items.splice(a,l.length,...l),i.items=[...i.items]},i.$connector.clear=function(a,l){const n=Math.min(l,i.items.length-a);i.$connector.set(a,[...Array(n)])},i.$connector.updateData=function(a){const l=a.reduce((n,o)=>(n[o.key]=o,n),{});i.items=i.items.map(n=>n&&(l[n.key]||n))},i.$connector.updateSize=function(a){const l=a-i.items.length;l>0?i.items=[...i.items,...Array(l)]:l<0&&(i.items=i.items.slice(0,a))},i.$connector.setPlaceholderItem=function(a={}){a.__placeholder=!0,i.$connector.placeholderItem=a}}};u("vaadin-grid-tree-toggle",p`
    :host {
      --vaadin-grid-tree-toggle-level-offset: 2em;
      align-items: center;
      vertical-align: middle;
      transform: translateX(calc(var(--lumo-space-s) * -1));
      -webkit-tap-highlight-color: transparent;
    }

    :host(:not([leaf])) {
      cursor: default;
    }

    [part='toggle'] {
      display: inline-block;
      font-size: 1.5em;
      line-height: 1;
      width: 1em;
      height: 1em;
      text-align: center;
      color: var(--lumo-contrast-50pct);
      cursor: var(--lumo-clickable-cursor);
      /* Increase touch target area */
      padding: calc(1em / 3);
      margin: calc(1em / -3);
    }

    :host(:not([dir='rtl'])) [part='toggle'] {
      margin-right: 0;
    }

    @media (hover: hover) {
      :host(:hover) [part='toggle'] {
        color: var(--lumo-contrast-80pct);
      }
    }

    [part='toggle']::before {
      font-family: 'lumo-icons';
      display: inline-block;
      height: 100%;
    }

    :host(:not([expanded])) [part='toggle']::before {
      content: var(--lumo-icons-angle-right);
    }

    :host([expanded]) [part='toggle']::before {
      content: var(--lumo-icons-angle-right);
      transform: rotate(90deg);
    }

    /* Experimental support for hierarchy connectors, using an unsupported selector */
    :host([theme~='connectors']) #level-spacer {
      position: relative;
      z-index: -1;
      font-size: 1em;
      height: 1.5em;
    }

    :host([theme~='connectors']) #level-spacer::before {
      display: block;
      content: '';
      margin-top: calc(var(--lumo-space-m) * -1);
      height: calc(var(--lumo-space-m) + 3em);
      background-image: linear-gradient(
        to right,
        transparent calc(var(--vaadin-grid-tree-toggle-level-offset) - 1px),
        var(--lumo-contrast-10pct) calc(var(--vaadin-grid-tree-toggle-level-offset) - 1px)
      );
      background-size: var(--vaadin-grid-tree-toggle-level-offset) var(--vaadin-grid-tree-toggle-level-offset);
      background-position: calc(var(--vaadin-grid-tree-toggle-level-offset) / 2 - 2px) 0;
    }

    /* RTL specific styles */

    :host([dir='rtl']) {
      margin-left: 0;
      margin-right: calc(var(--lumo-space-s) * -1);
    }

    :host([dir='rtl']) [part='toggle'] {
      margin-left: 0;
    }

    :host([dir='rtl'][expanded]) [part='toggle']::before {
      transform: rotate(-90deg);
    }

    :host([dir='rtl'][theme~='connectors']) #level-spacer::before {
      background-image: linear-gradient(
        to left,
        transparent calc(var(--vaadin-grid-tree-toggle-level-offset) - 1px),
        var(--lumo-contrast-10pct) calc(var(--vaadin-grid-tree-toggle-level-offset) - 1px)
      );
      background-position: calc(100% - (var(--vaadin-grid-tree-toggle-level-offset) / 2 - 2px)) 0;
    }

    :host([dir='rtl']:not([expanded])) [part='toggle']::before,
    :host([dir='rtl'][expanded]) [part='toggle']::before {
      content: var(--lumo-icons-angle-left);
    }
  `,{moduleId:"lumo-grid-tree-toggle"});/**
 * @license
 * Copyright (c) 2016 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const qs=document.createElement("template");qs.innerHTML=`
  <style>
    @font-face {
      font-family: "vaadin-grid-tree-icons";
      src: url(data:application/font-woff;charset=utf-8;base64,d09GRgABAAAAAAQkAA0AAAAABrwAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABGRlRNAAAECAAAABoAAAAcgHwa6EdERUYAAAPsAAAAHAAAAB4AJwAOT1MvMgAAAZQAAAA/AAAAYA8TBIJjbWFwAAAB8AAAAFUAAAFeGJvXWmdhc3AAAAPkAAAACAAAAAgAAAAQZ2x5ZgAAAlwAAABLAAAAhIrPOhFoZWFkAAABMAAAACsAAAA2DsJI02hoZWEAAAFcAAAAHQAAACQHAgPHaG10eAAAAdQAAAAZAAAAHAxVAgBsb2NhAAACSAAAABIAAAASAIAAVG1heHAAAAF8AAAAGAAAACAACgAFbmFtZQAAAqgAAAECAAACTwflzbdwb3N0AAADrAAAADYAAABZQ7Ajh3icY2BkYGAA4twv3Vfi+W2+MnCzMIDANSOmbGSa2YEZRHEwMIEoAAoiB6sAeJxjYGRgYD7w/wADAwsDCDA7MDAyoAI2AFEEAtIAAAB4nGNgZGBg4GBgZgDRDAxMDGgAAAGbABB4nGNgZp7JOIGBlYGBaSbTGQYGhn4IzfiawZiRkwEVMAqgCTA4MDA+38d84P8BBgdmIAapQZJVYGAEAGc/C54AeJxjYYAAxlAIzQTELAwMBxgZGB0ACy0BYwAAAHicY2BgYGaAYBkGRgYQiADyGMF8FgYbIM3FwMHABISMDArP9/3/+/8/WJXC8z0Q9v8nEp5gHVwMMMAIMo+RDYiZoQJMQIKJARUA7WBhGN4AACFKDtoAAAAAAAAAAAgACAAQABgAJgA0AEIAAHichYvBEYBADAKBVHBjBT4swl9KS2k05o0XHd/yW1hAfBFwCv9sIlJu3nZaNS3PXAaXXHI8Lge7DlzF7C1RgXc7xkK6+gvcD2URmQB4nK2RQWoCMRiFX3RUqtCli65yADModOMBLLgQSqHddRFnQghIAnEUvEA3vUUP0LP0Fj1G+yb8R5iEhO9/ef/7FwFwj28o9EthiVp4hBlehcfUP4Ur8o/wBAv8CU+xVFvhOR7UB7tUdUdlVRJ6HnHWTnhM/V24In8JT5j/KzzFSi2E53hUz7jCcrcIiDDwyKSW1JEct2HdIPH1DFytbUM0PofWdNk5E5oUqb/Q6HHBiVGZpfOXkyUMEj5IyBuNmYZQjBobfsuassvnkKLe1OuBBj0VQ8cRni2xjLWsHaM0jrjx3peYA0/vrdmUYqe9iy7bzrX6eNP7Jh1SijX+AaUVbB8AAHicY2BiwA84GBgYmRiYGJkZmBlZGFkZ2djScyoLMgzZS/MyDQwMwLSruZMzlHaB0q4A76kLlwAAAAEAAf//AA94nGNgZGBg4AFiMSBmYmAEQnYgZgHzGAAD6wA2eJxjYGBgZACCKxJigiD6mhFTNowGACmcA/8AAA==) format('woff');
      font-weight: normal;
      font-style: normal;
    }
  </style>
`;document.head.appendChild(qs.content);class ki extends y(vt(g)){static get template(){return f`
      <style>
        :host {
          display: inline-flex;
          align-items: baseline;
          max-width: 100%;

          /* CSS API for :host */
          --vaadin-grid-tree-toggle-level-offset: 1em;
          --_collapsed-icon: '\\e7be\\00a0';
        }

        :host([dir='rtl']) {
          --_collapsed-icon: '\\e7bd\\00a0';
        }

        :host([hidden]) {
          display: none !important;
        }

        :host(:not([leaf])) {
          cursor: pointer;
        }

        #level-spacer,
        [part='toggle'] {
          flex: none;
        }

        #level-spacer {
          display: inline-block;
          width: calc(var(---level, '0') * var(--vaadin-grid-tree-toggle-level-offset));
        }

        [part='toggle']::before {
          font-family: 'vaadin-grid-tree-icons';
          line-height: 1em; /* make icon font metrics not affect baseline */
        }

        :host(:not([expanded])) [part='toggle']::before {
          content: var(--_collapsed-icon);
        }

        :host([expanded]) [part='toggle']::before {
          content: '\\e7bc\\00a0'; /* icon glyph + single non-breaking space */
        }

        :host([leaf]) [part='toggle'] {
          visibility: hidden;
        }

        slot {
          display: block;
          overflow: hidden;
          text-overflow: ellipsis;
        }
      </style>

      <span id="level-spacer"></span>
      <span part="toggle"></span>
      <slot></slot>
    `}static get is(){return"vaadin-grid-tree-toggle"}static get properties(){return{level:{type:Number,value:0,observer:"_levelChanged"},leaf:{type:Boolean,value:!1,reflectToAttribute:!0},expanded:{type:Boolean,value:!1,reflectToAttribute:!0,notify:!0}}}ready(){super.ready(),this.addEventListener("click",e=>this._onClick(e))}_onClick(e){this.leaf||Mr(e.target)||(e.preventDefault(),this.expanded=!this.expanded)}_levelChanged(e){const t=Number(e).toString();this.style.setProperty("---level",t)}}customElements.define(ki.is,ki);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Ti extends Fr{static get is(){return"vaadin-integer-field"}constructor(){super(),this.allowedCharPattern="[-+\\d]"}_valueChanged(e,t){if(e!==""&&!this.__isInteger(e)){console.warn(`Trying to set non-integer value "${e}" to <vaadin-integer-field>. Clearing the value.`),this.value="";return}super._valueChanged(e,t)}_stepChanged(e,t){if(e!=null&&!this.__hasOnlyDigits(e)){console.warn(`<vaadin-integer-field> The \`step\` property must be a positive integer but \`${e}\` was provided, so the property was reset to \`null\`.`),this.step=null;return}super._stepChanged(e,t)}__isInteger(e){return/^(-\d)?\d*$/.test(String(e))}__hasOnlyDigits(e){return/^\d+$/.test(String(e))}}customElements.define(Ti.is,Ti);const to=p`
  :host {
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
  }

  [part='backdrop'] {
    background: var(--lumo-base-color) linear-gradient(var(--lumo-shade-5pct), var(--lumo-shade-5pct));
  }

  [part='content'] {
    padding: 0;
  }

  [part='overlay'] {
    background: none;
    border-radius: 0;
    box-shadow: none;
    width: 100%;
    height: 100%;
  }

  [part='card'] {
    width: calc(var(--lumo-size-m) * 10);
    background: var(--lumo-base-color) linear-gradient(var(--lumo-tint-5pct), var(--lumo-tint-5pct));
  }

  [part='brand'] {
    padding: var(--lumo-space-l) var(--lumo-space-xl) var(--lumo-space-l) var(--lumo-space-l);
    background-color: var(--lumo-primary-color);
    color: var(--lumo-primary-contrast-color);
    min-height: calc(var(--lumo-size-m) * 5);
  }

  [part='description'] {
    line-height: var(--lumo-line-height-s);
    color: var(--lumo-tint-70pct);
    margin-bottom: 0;
  }

  [part='content'] {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  [part='card'] {
    border-radius: var(--lumo-border-radius-l);
    box-shadow: var(--lumo-box-shadow-s);
    margin: var(--lumo-space-s);
    height: auto;
  }

  /* Small screen */
  @media only screen and (max-width: 500px) {
    [part='overlay'],
    [part='content'] {
      height: 100%;
    }

    [part='content'] {
      min-height: 100%;
      background: var(--lumo-base-color);
      align-items: flex-start;
    }

    [part='card'],
    [part='overlay'] {
      width: 100%;
      border-radius: 0;
      box-shadow: none;
      margin: 0;
    }

    /* RTL styles */
    :host([dir='rtl']) [part='brand'] {
      padding: var(--lumo-space-l) var(--lumo-space-l) var(--lumo-space-l) var(--lumo-space-xl);
    }
  }

  /* Landscape small screen */
  @media only screen and (max-height: 600px) and (min-width: 600px) and (orientation: landscape) {
    [part='card'] {
      flex-direction: row;
      align-items: stretch;
      max-width: calc(var(--lumo-size-m) * 16);
      width: 100%;
    }

    [part='brand'],
    [part='form'] {
      flex: auto;
      flex-basis: 0;
      box-sizing: border-box;
    }

    [part='brand'] {
      justify-content: flex-start;
    }

    [part='form'] {
      padding: var(--lumo-space-l);
      overflow: auto;
    }
  }

  /* Landscape really small screen */
  @media only screen and (max-height: 500px) and (min-width: 600px) and (orientation: landscape),
    only screen and (max-width: 600px) and (min-width: 600px) and (orientation: landscape) {
    [part='content'] {
      height: 100vh;
    }

    [part='card'] {
      margin: 0;
      width: 100%;
      max-width: none;
      height: 100%;
      flex: auto;
      border-radius: 0;
      box-shadow: none;
    }

    [part='form'] {
      height: 100%;
      overflow: auto;
      -webkit-overflow-scrolling: touch;
    }
  }

  /* Handle iPhone X notch */
  @media only screen and (device-width: 375px) and (device-height: 812px) and (-webkit-device-pixel-ratio: 3) {
    [part='card'] {
      padding-right: constant(safe-area-inset-right);
      padding-right: env(safe-area-inset-right);

      padding-left: constant(safe-area-inset-left);
      padding-left: env(safe-area-inset-left);
    }

    [part='brand'] {
      margin-left: calc(constant(safe-area-inset-left) * -1);
      margin-left: calc(env(safe-area-inset-left) * -1);

      padding-left: calc(var(--lumo-space-l) + constant(safe-area-inset-left));
      padding-left: calc(var(--lumo-space-l) + env(safe-area-inset-left));
    }

    /* RTL styles */
    :host([dir='rtl']) [part='card'] {
      padding-left: constant(safe-area-inset-right);
      padding-left: env(safe-area-inset-right);
      padding-right: constant(safe-area-inset-left);
      padding-right: env(safe-area-inset-left);
    }

    :host([dir='rtl']) [part='brand'] {
      margin-right: calc(constant(safe-area-inset-left) * -1);
      margin-right: calc(env(safe-area-inset-left) * -1);
      padding-right: calc(var(--lumo-space-l) + constant(safe-area-inset-left));
      padding-right: calc(var(--lumo-space-l) + env(safe-area-inset-left));
    }
  }
`;u("vaadin-login-overlay-wrapper",[ts,is,to],{moduleId:"vaadin-login-overlay-wrapper-lumo-styles"});const io=p`
  :host([theme~='with-overlay']) {
    min-height: 100%;
    display: flex;
    justify-content: center;
    max-width: 100%;
  }

  /* Landscape small screen */
  @media only screen and (max-height: 600px) and (min-width: 600px) and (orientation: landscape) {
    :host([theme~='with-overlay']) [part='form'] {
      height: 100%;
      -webkit-overflow-scrolling: touch;
      flex: 1;
      padding: 2px;
    }
  }
`;u("vaadin-login-form-wrapper",[ts,is,io],{moduleId:"lumo-login-overlay"});/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Qs=document.createElement("template");Qs.innerHTML=`<dom-module id="vaadin-login-overlay-wrapper-template">
  <template>
    <style>
      [part="overlay"] {
        outline: none;
      }

      [part="card"] {
        max-width: 100%;
        box-sizing: border-box;
        overflow: hidden;
        display: flex;
        flex-direction: column;
      }

      [part="brand"] {
        box-sizing: border-box;
        overflow: hidden;
        flex-grow: 1;
        flex-shrink: 0;
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
      }

      [part="brand"] h1 {
        color: inherit;
        margin: 0;
      }
    </style>
    <section part="card">
      <div part="brand">
        <slot name="title">
          <h1 part="title">[[title]]</h1>
        </slot>
        <p part="description">[[description]]</p>
      </div>
      <div part="form">
        <slot></slot>
      </div>
    </section>
  </template>
</dom-module>`;document.head.appendChild(Qs.content);let ve;class Pi extends Me{static get is(){return"vaadin-login-overlay-wrapper"}static get properties(){return{title:{type:String},description:{type:String}}}static get template(){if(!ve){ve=super.template.cloneNode(!0);const e=Or.import(`${this.is}-template`,"template"),t=e.content.querySelector("[part=card]"),s=e.content.querySelector("style"),r=ve.content.querySelector("#content");r.replaceChild(t,r.children[0]),r.appendChild(s)}return ve}}customElements.define(Pi.is,Pi);/**
 * @license
 * Copyright (c) 2018 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Bi extends Rr(A(y(g))){static get template(){return f`
      <vaadin-login-overlay-wrapper
        id="vaadinLoginOverlayWrapper"
        opened="{{opened}}"
        focus-trap
        with-backdrop
        title="[[title]]"
        description="[[description]]"
        theme$="[[_theme]]"
      >
        <vaadin-login-form
          theme="with-overlay"
          id="vaadinLoginForm"
          action="{{action}}"
          disabled="{{disabled}}"
          error="{{error}}"
          no-autofocus="[[noAutofocus]]"
          no-forgot-password="{{noForgotPassword}}"
          i18n="{{i18n}}"
          on-login="_retargetEvent"
          on-forgot-password="_retargetEvent"
        ></vaadin-login-form>
      </vaadin-login-overlay-wrapper>
    `}static get is(){return"vaadin-login-overlay"}static get properties(){return{description:{type:String,value:"Application description",notify:!0},opened:{type:Boolean,value:!1,observer:"_onOpenedChange"},title:{type:String,value:"App name"}}}static get observers(){return["__i18nChanged(i18n.header.*)"]}ready(){super.ready(),this._preventClosingLogin=this._preventClosingLogin.bind(this)}connectedCallback(){super.connectedCallback(),this.$.vaadinLoginOverlayWrapper.addEventListener("vaadin-overlay-outside-click",this._preventClosingLogin),this.$.vaadinLoginOverlayWrapper.addEventListener("vaadin-overlay-escape-press",this._preventClosingLogin),this.__restoreOpened&&(this.$.vaadinLoginOverlayWrapper.opened=!0)}disconnectedCallback(){super.disconnectedCallback(),this.$.vaadinLoginOverlayWrapper.removeEventListener("vaadin-overlay-outside-click",this._preventClosingLogin),this.$.vaadinLoginOverlayWrapper.removeEventListener("vaadin-overlay-escape-press",this._preventClosingLogin),this.__restoreOpened=this.$.vaadinLoginOverlayWrapper.opened,this.$.vaadinLoginOverlayWrapper.opened=!1}__i18nChanged(e){const t=e.base;!t||(this.title=t.title,this.description=t.description)}_preventClosingLogin(e){e.preventDefault()}_onOpenedChange(){this.opened?(this._undoTeleport=this._teleport(this._getElementsToTeleport()),document.body.style.pointerEvents=this.$.vaadinLoginOverlayWrapper._previousDocumentPointerEvents):(this.$.vaadinLoginForm.$.vaadinLoginUsername.value="",this.$.vaadinLoginForm.$.vaadinLoginPassword.value="",this.disabled=!1,this._undoTeleport&&this._undoTeleport())}_teleport(e){const t=Array.from(e).map(s=>this.$.vaadinLoginOverlayWrapper.appendChild(s));return()=>{for(;t.length>0;)this.appendChild(t.shift())}}_getElementsToTeleport(){return this.querySelectorAll("[slot=title]")}}customElements.define(Bi.is,Bi);const so=p`
  :host {
    margin: calc(var(--lumo-space-xs) / 2);
    margin-left: 0;
    border-radius: 0;
  }

  [part='label'] {
    width: 100%;
  }

  /* NOTE(web-padawan): avoid using shorthand padding property for IE11 */
  [part='label'] ::slotted(vaadin-context-menu-item) {
    justify-content: center;
    background-color: transparent;
    height: var(--lumo-button-size);
    margin: 0 calc((var(--lumo-size-m) / 3 + var(--lumo-border-radius-m) / 2) * -1);
    padding-left: calc(var(--lumo-size-m) / 3 + var(--lumo-border-radius-m) / 2);
    padding-right: calc(var(--lumo-size-m) / 3 + var(--lumo-border-radius-m) / 2);
  }

  :host([theme~='small']) [part='label'] ::slotted(vaadin-context-menu-item) {
    min-height: var(--lumo-size-s);
    margin: 0 calc((var(--lumo-size-s) / 3 + var(--lumo-border-radius-m) / 2) * -1);
    padding-left: calc(var(--lumo-size-s) / 3 + var(--lumo-border-radius-m) / 2);
    padding-right: calc(var(--lumo-size-s) / 3 + var(--lumo-border-radius-m) / 2);
  }

  :host([theme~='tertiary']) [part='label'] ::slotted(vaadin-context-menu-item) {
    margin: 0 calc((var(--lumo-button-size) / 6) * -1);
    padding-left: calc(var(--lumo-button-size) / 6);
    padding-right: calc(var(--lumo-button-size) / 6);
  }

  :host([theme~='tertiary-inline']) {
    margin-top: calc(var(--lumo-space-xs) / 2);
    margin-bottom: calc(var(--lumo-space-xs) / 2);
    margin-right: calc(var(--lumo-space-xs) / 2);
  }

  :host([theme~='tertiary-inline']) [part='label'] ::slotted(vaadin-context-menu-item) {
    margin: 0;
    padding: 0;
  }

  :host(:first-of-type) {
    border-radius: var(--lumo-border-radius-m) 0 0 var(--lumo-border-radius-m);

    /* Needed to retain the focus-ring with border-radius */
    margin-left: calc(var(--lumo-space-xs) / 2);
  }

  :host(:nth-last-of-type(2)),
  :host([part='overflow-button']) {
    border-radius: 0 var(--lumo-border-radius-m) var(--lumo-border-radius-m) 0;
  }

  :host([theme~='tertiary']),
  :host([theme~='tertiary-inline']) {
    border-radius: var(--lumo-border-radius-m);
  }

  :host([part='overflow-button']) {
    min-width: var(--lumo-button-size);
    padding-left: calc(var(--lumo-button-size) / 4);
    padding-right: calc(var(--lumo-button-size) / 4);
  }

  :host([part='overflow-button']) ::slotted(*) {
    font-size: var(--lumo-font-size-xl);
  }

  :host([part='overflow-button']) [part='prefix'],
  :host([part='overflow-button']) [part='suffix'] {
    margin-left: 0;
    margin-right: 0;
  }

  /* RTL styles */
  :host([dir='rtl']) {
    margin-left: calc(var(--lumo-space-xs) / 2);
    margin-right: 0;
    border-radius: 0;
  }

  :host([dir='rtl']:first-of-type) {
    border-radius: 0 var(--lumo-border-radius-m) var(--lumo-border-radius-m) 0;
    margin-right: calc(var(--lumo-space-xs) / 2);
  }

  :host([dir='rtl']:nth-last-of-type(2)),
  :host([dir='rtl'][part='overflow-button']) {
    border-radius: var(--lumo-border-radius-m) 0 0 var(--lumo-border-radius-m);
  }
`;u("vaadin-menu-bar-button",[Dr,so],{moduleId:"lumo-menu-bar-button"});/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-menu-bar-button",p`
    [part='label'] ::slotted(vaadin-context-menu-item) {
      position: relative;
      z-index: 1;
    }
  `,{moduleId:"vaadin-menu-bar-button-styles"});class Mi extends ss{static get is(){return"vaadin-menu-bar-button"}}customElements.define(Mi.is,Mi);u("vaadin-context-menu-item",p`
    :host([theme='menu-bar-item']) [part='content'] {
      display: flex;
      /* tweak to inherit centering from menu bar button */
      align-items: inherit;
      justify-content: inherit;
    }

    :host([theme='menu-bar-item']) [part='content'] ::slotted(vaadin-icon),
    :host([theme='menu-bar-item']) [part='content'] ::slotted(iron-icon) {
      display: inline-block;
      width: var(--lumo-icon-size-m);
      height: var(--lumo-icon-size-m);
    }

    :host([theme='menu-bar-item']) [part='content'] ::slotted(vaadin-icon[icon^='vaadin:']),
    :host([theme='menu-bar-item']) [part='content'] ::slotted(iron-icon[icon^='vaadin:']) {
      padding: var(--lumo-space-xs);
      box-sizing: border-box !important;
    }
  `,{moduleId:"lumo-menu-bar-item"});u("vaadin-context-menu-overlay",p`
    :host(:first-of-type) {
      padding-top: var(--lumo-space-xs);
    }
  `,{moduleId:"lumo-menu-bar-overlay"});u("vaadin-menu-bar",p`
    :host([has-single-button]) [part$='button'] {
      border-radius: var(--lumo-border-radius-m);
    }

    :host([theme~='end-aligned']) [part$='button']:first-child,
    :host([theme~='end-aligned'][has-single-button]) [part$='button'] {
      margin-inline-start: auto;
    }
  `,{moduleId:"lumo-menu-bar"});/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Fi extends Lr{static get is(){return"vaadin-menu-bar-submenu"}constructor(){super(),this.openOn="opensubmenu"}_openedChanged(e){this.$.overlay.opened=e}close(){super.close(),this.hasAttribute("is-root")&&this.getRootNode().host._close()}}customElements.define(Fi.is,Fi);/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const ro=i=>class extends ft(i){static get properties(){return{_hasOverflow:{type:Boolean,value:!1}}}static get observers(){return["_menuItemsChanged(items, items.splices)"]}get _observeParent(){return!0}ready(){super.ready(),this.setAttribute("role","menubar")}connectedCallback(){super.connectedCallback(),this._initButtonAttrs(this._overflow)}get _buttons(){return Array.from(this.shadowRoot.querySelectorAll('[part$="button"]'))}get _container(){return this.shadowRoot.querySelector('[part="container"]')}get _overflow(){return this.shadowRoot.querySelector('[part="overflow-button"]')}_menuItemsChanged(e){e!==this._oldItems&&(this._oldItems=e,this.__renderButtons(e))}__getOverflowCount(e){return e.item&&e.item.children&&e.item.children.length||0}__restoreButtons(e){for(let t=0;t<e.length;t++){const s=e[t];s.disabled=s.item&&s.item.disabled||this.disabled,s.style.visibility="",s.style.position="";const r=s.item&&s.item.component;r instanceof HTMLElement&&r.classList.contains("vaadin-menu-item")&&(s.appendChild(r),r.classList.remove("vaadin-menu-item"))}this.__updateOverflow([])}__updateOverflow(e){this._overflow.item={children:e},this._hasOverflow=e.length>0}__setOverflowItems(e,t){const s=this._container;if(s.offsetWidth<s.scrollWidth){this._hasOverflow=!0;const r=this.getAttribute("dir")==="rtl";let a;for(a=e.length;a>0;a--){const n=e[a-1],o=getComputedStyle(n);if(!r&&n.offsetLeft+n.offsetWidth<s.offsetWidth-t.offsetWidth||r&&n.offsetLeft>=t.offsetWidth)break;n.disabled=!0,n.style.visibility="hidden",n.style.position="absolute",n.style.width=o.width}const l=e.filter((n,o)=>o>=a).map(n=>n.item);this.__updateOverflow(l)}}__detectOverflow(){const e=this._overflow,t=this._buttons.filter(l=>l!==e),s=this.__getOverflowCount(e);this.__restoreButtons(t),this.__setOverflowItems(t,e);const r=this.__getOverflowCount(e);s!==r&&this._subMenu.opened&&this._subMenu.close();const a=r===t.length||r===0&&t.length===1;this.toggleAttribute("has-single-button",a)}_removeButtons(){const e=this._container;for(;e.children.length>1;)e.removeChild(e.firstElementChild)}_initButton(e){const t=document.createElement("vaadin-menu-bar-button");t.setAttribute("part","menu-bar-button");const s={...e};if(t.item=s,e.component){const r=this.__getComponent(s);s.component=r,r.item=s,t.appendChild(r)}else e.text&&(t.textContent=e.text);return t}_initButtonAttrs(e){e.setAttribute("role","menuitem"),(e===this._overflow||e.item&&e.item.children)&&(e.setAttribute("aria-haspopup","true"),e.setAttribute("aria-expanded","false"))}_setButtonDisabled(e,t){e.disabled=t,e.setAttribute("tabindex",t?"-1":"0")}_setButtonTheme(e,t){let s=t;const r=e.item&&e.item.theme;r!=null&&(s=Array.isArray(r)?r.join(" "):r),s?e.setAttribute("theme",s):e.removeAttribute("theme")}_appendButton(e){this._container.insertBefore(e,this._overflow)}__getComponent(e){const t=e.component;let s;const r=t instanceof HTMLElement;if(r&&t.localName==="vaadin-context-menu-item"?s=t:(s=document.createElement("vaadin-context-menu-item"),s.appendChild(r?t:document.createElement(t))),e.text){const a=s.firstChild||s;a.textContent=e.text}return s.setAttribute("theme","menu-bar-item"),s}__renderButtons(e=[]){this._removeButtons(),e.length!==0&&(e.forEach(t=>{const s=this._initButton(t);this._appendButton(s),this._setButtonDisabled(s,t.disabled),this._initButtonAttrs(s),this._setButtonTheme(s,this._theme)}),this.__detectOverflow())}_onResize(){this.__detectOverflow()}};/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const no=i=>class extends i{static get properties(){return{openOnHover:{type:Boolean}}}constructor(){super(),this.__boundOnContextMenuKeydown=this.__onContextMenuKeydown.bind(this)}static get observers(){return["_itemsChanged(items, items.splices)"]}ready(){super.ready(),this.addEventListener("keydown",r=>this._onKeydown(r)),this.addEventListener("focusin",r=>this._onFocusin(r)),this._subMenu.addEventListener("item-selected",this.__onItemSelected.bind(this)),this._subMenu.addEventListener("close-all-menus",this.__onEscapeClose.bind(this)),this._subMenu.$.overlay.addEventListener("keydown",this.__boundOnContextMenuKeydown);const s=this._container;s.addEventListener("click",this.__onButtonClick.bind(this)),s.addEventListener("mouseover",r=>this._onMouseOver(r))}get __isRTL(){return this.getAttribute("dir")==="rtl"}_setExpanded(t,s){t.toggleAttribute("expanded",s),t.toggleAttribute("active",s),t.setAttribute("aria-expanded",s?"true":"false")}_setTabindex(t,s){t.setAttribute("tabindex",s?"0":"-1")}_focusButton(t){t.focus(),t.setAttribute("focus-ring",""),this._buttons.forEach(s=>{this._setTabindex(s,s===t)})}_getButtonFromEvent(t){return Array.from(t.composedPath()).filter(s=>s.localName==="vaadin-menu-bar-button")[0]}_getCurrentButton(){return this.shadowRoot.activeElement||this._expandedButton}_onFocusin(){const t=this.shadowRoot.querySelector('[part$="button"][tabindex="0"]');t&&this._buttons.forEach(s=>{this._setTabindex(s,s===t)})}_onKeydown(t){const s=this._getButtonFromEvent(t);s&&(t.keyCode===40?(t.preventDefault(),s===this._expandedButton?this._focusFirstItem():this.__openSubMenu(s,t)):t.keyCode===38?(t.preventDefault(),s===this._expandedButton?this._focusLastItem():this.__openSubMenu(s,t,{focusLast:!0})):t.keyCode===27&&s===this._expandedButton?this._close(!0):this._navigateByKey(t))}_navigateByKey(t){const s=t.key.replace(/^Arrow/,""),r=this._buttons,a=this._getCurrentButton(),l=r.indexOf(a);let n,o;const d=this.__isRTL?-1:1;switch(s){case"Left":o=-d,n=l-d;break;case"Right":o=d,n=l+d;break;case"Home":o=1,n=0;break;case"End":o=-1,n=r.length-1;break}if(n=this._getAvailableIndex(n,o,r),n>=0){t.preventDefault();const h=r[n],c=a===this._expandedButton;c&&this._close(),this._focusButton(h),c&&h.item&&h.item.children&&this.__openSubMenu(h,t,{keepFocus:!0})}}_getAvailableIndex(t,s,r){const a=r.length;let l=t;for(let n=0;typeof l=="number"&&n<a;n++,l+=s||1){l<0?l=a-1:l>=a&&(l=0);const o=r[l];if(!o.disabled&&!o.hasAttribute("hidden"))return l}return-1}get _subMenu(){return this.shadowRoot.querySelector("vaadin-menu-bar-submenu")}_itemsChanged(){const t=this._subMenu;t&&t.opened&&t.close()}_onMouseOver(t){const s=this._getButtonFromEvent(t);if(s&&s!==this._expandedButton){const r=this._subMenu.opened;s.item.children&&(this.openOnHover||r)?this.__openSubMenu(s,t):r&&this._close()}}__onContextMenuKeydown(t){const s=Array.from(t.composedPath()).filter(r=>r._item)[0];if(s){const r=s.parentNode;if(t.keyCode===38&&s===r.items[0]&&this._close(!0),t.keyCode===37||t.keyCode===39&&!s._item.children){t.stopImmediatePropagation(),this._navigateByKey(t);const a=this.shadowRoot.activeElement;a&&a.item&&a.item.children&&this.__openSubMenu(a,t,{keepFocus:!0})}}}__fireItemSelected(t){this.dispatchEvent(new CustomEvent("item-selected",{detail:{value:t}}))}__onButtonClick(t){t.stopPropagation();const s=this._getButtonFromEvent(t);s&&this.__openSubMenu(s,t)}__openSubMenu(t,s,r={}){const a=this._subMenu,l=t.item;if(a.opened&&(this._close(),a.listenOn===t))return;const n=l&&l.children;if(!n||n.length===0){this.__fireItemSelected(l);return}a.items=n,a.listenOn=t;const o=a.$.overlay;o.positionTarget=t,o.noVerticalOverlap=!0,this._expandedButton=t,requestAnimationFrame(()=>{t.dispatchEvent(new CustomEvent("opensubmenu",{detail:{children:n}})),this._setExpanded(t,!0)}),r.focusLast&&this.__onceOpened(()=>this._focusLastItem()),r.keepFocus&&this.__onceOpened(()=>{this._focusButton(this._expandedButton)}),this.__onceOpened(()=>{s.type!=="keydown"&&a.$.overlay.$.overlay.focus(),o._updatePosition()})}_focusFirstItem(){this._subMenu.$.overlay.firstElementChild.focus()}_focusLastItem(){const t=this._subMenu.$.overlay.firstElementChild,s=t.items[t.items.length-1];s&&s.focus()}__onceOpened(t){this.style.pointerEvents="auto";const s=this._subMenu.$.overlay,r=()=>{t(),s.removeEventListener("vaadin-overlay-open",r)};s.addEventListener("vaadin-overlay-open",r)}__onItemSelected(t){t.stopPropagation(),this._close(),this.__fireItemSelected(t.detail.value)}__onEscapeClose(){this.__deactivateButton(!0)}__deactivateButton(t){const s=this._expandedButton;s&&s.hasAttribute("expanded")&&(this._setExpanded(s,!1),t&&this._focusButton(s),this._expandedButton=null)}_close(t){this.style.pointerEvents="",this.__deactivateButton(t),this._subMenu.opened&&this._subMenu.close()}};/**
 * @license
 * Copyright (c) 2019 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Oi extends ro(Re(no(A(y(g))))){static get template(){return f`
      <style>
        :host {
          display: block;
        }

        :host([hidden]) {
          display: none !important;
        }

        [part='container'] {
          position: relative;
          display: flex;
          width: 100%;
          flex-wrap: nowrap;
          overflow: hidden;
        }

        [part$='button'] {
          flex-shrink: 0;
        }

        [part='overflow-button'] {
          margin-right: 0;
        }

        .dots::before {
          display: block;
          content: '\\00B7\\00B7\\00B7';
          font-size: inherit;
          line-height: inherit;
        }
      </style>

      <div part="container">
        <vaadin-menu-bar-button part="overflow-button" hidden$="[[!_hasOverflow]]" aria-label$="[[i18n.moreOptions]]">
          <div class="dots"></div>
        </vaadin-menu-bar-button>
      </div>
      <vaadin-menu-bar-submenu is-root=""></vaadin-menu-bar-submenu>
    `}static get is(){return"vaadin-menu-bar"}static get properties(){return{items:{type:Array,value:()=>[]},i18n:{type:Object,value:()=>({moreOptions:"More options"})}}}static get observers(){return["_themeChanged(_theme)"]}_disabledChanged(e,t){super._disabledChanged(e,t),t!==e&&this.__updateButtonsDisabled(e)}_themeChanged(e){this.shadowRoot&&(this._buttons.forEach(t=>this._setButtonTheme(t,e)),this.__detectOverflow()),e?this._subMenu.setAttribute("theme",e):this._subMenu.removeAttribute("theme")}__updateButtonsDisabled(e){this._buttons.forEach(t=>{t.disabled=e||t.item&&t.item.disabled})}}customElements.define(Oi.is,Oi);u("vaadin-message-input",p`
    :host {
      padding: var(--lumo-space-s) var(--lumo-space-m);
    }
  `,{moduleId:"lumo-message-input"});/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const ao=p`
  [part='input-field'],
  [part='input-field'] ::slotted(textarea) {
    height: auto;
    box-sizing: border-box;
  }

  [part='input-field'] {
    /* Equal to the implicit padding in vaadin-text-field */
    padding-top: calc((var(--lumo-text-field-size) - 1em * var(--lumo-line-height-s)) / 2);
    padding-bottom: calc((var(--lumo-text-field-size) - 1em * var(--lumo-line-height-s)) / 2);
    transition: background-color 0.1s;
    line-height: var(--lumo-line-height-s);
  }

  :host(:not([readonly])) [part='input-field']::after {
    display: none;
  }

  :host([readonly]) [part='input-field'] {
    border: 1px dashed var(--lumo-contrast-30pct);
  }

  :host([readonly]) [part='input-field']::after {
    border: none;
  }

  :host(:hover:not([readonly]):not([focused]):not([invalid])) [part='input-field'] {
    background-color: var(--lumo-contrast-20pct);
  }

  @media (pointer: coarse) {
    :host(:hover:not([readonly]):not([focused]):not([invalid])) [part='input-field'] {
      background-color: var(--lumo-contrast-10pct);
    }

    :host(:active:not([readonly]):not([focused])) [part='input-field'] {
      background-color: var(--lumo-contrast-20pct);
    }
  }

  [part='input-field'] ::slotted(textarea) {
    line-height: inherit;
    --_lumo-text-field-overflow-mask-image: none;
  }

  /* Vertically align icon prefix/suffix with the first line of text */
  [part='input-field'] ::slotted(iron-icon),
  [part='input-field'] ::slotted(vaadin-icon) {
    margin-top: calc((var(--lumo-icon-size-m) - 1em * var(--lumo-line-height-s)) / -2);
  }
`;u("vaadin-text-area",[De,ao],{moduleId:"lumo-text-area"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class oo extends rs{constructor(e,t){super(e,"textarea",()=>document.createElement("textarea"),(s,r)=>{const a=s.getAttribute("value");a&&(r.value=a);const l=s.getAttribute("name");l&&r.setAttribute("name",l),r.id=this.defaultId,typeof t=="function"&&t(r)},!0)}}/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-text-area",gt,{moduleId:"vaadin-text-area-styles"});class lt extends ft(Xi(zr(y(A(g))))){static get is(){return"vaadin-text-area"}static get template(){return f`
      <style>
        :host {
          animation: 1ms vaadin-text-area-appear;
        }

        .vaadin-text-area-container {
          flex: auto;
        }

        /* The label, helper text and the error message should neither grow nor shrink. */
        [part='label'],
        [part='helper-text'],
        [part='error-message'] {
          flex: none;
        }

        [part='input-field'] {
          flex: auto;
          overflow: auto;
          -webkit-overflow-scrolling: touch;
        }

        ::slotted(textarea) {
          -webkit-appearance: none;
          -moz-appearance: none;
          flex: auto;
          overflow: hidden;
          width: 100%;
          height: 100%;
          outline: none;
          resize: none;
          margin: 0;
          padding: 0 0.25em;
          border: 0;
          border-radius: 0;
          min-width: 0;
          font: inherit;
          font-size: 1em;
          line-height: normal;
          color: inherit;
          background-color: transparent;
          /* Disable default invalid style in Firefox */
          box-shadow: none;
        }

        /* Override styles from <vaadin-input-container> */
        [part='input-field'] ::slotted(textarea) {
          align-self: stretch;
          white-space: pre-wrap;
        }

        [part='input-field'] ::slotted(:not(textarea)) {
          align-self: flex-start;
        }

        /* Workaround https://bugzilla.mozilla.org/show_bug.cgi?id=1739079 */
        :host([disabled]) ::slotted(textarea) {
          user-select: none;
        }

        @keyframes vaadin-text-area-appear {
          to {
            opacity: 1;
          }
        }
      </style>

      <div class="vaadin-text-area-container">
        <div part="label">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true"></span>
        </div>

        <vaadin-input-container
          part="input-field"
          readonly="[[readonly]]"
          disabled="[[disabled]]"
          invalid="[[invalid]]"
          theme$="[[_theme]]"
          on-scroll="__scrollPositionUpdated"
        >
          <slot name="prefix" slot="prefix"></slot>
          <slot name="textarea"></slot>
          <slot name="suffix" slot="suffix"></slot>
          <div id="clearButton" part="clear-button" slot="suffix" aria-hidden="true"></div>
        </vaadin-input-container>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get properties(){return{maxlength:{type:Number},minlength:{type:Number}}}static get delegateAttrs(){return[...super.delegateAttrs,"maxlength","minlength"]}static get constraints(){return[...super.constraints,"maxlength","minlength"]}get clearElement(){return this.$.clearButton}_onResize(){this.__scrollPositionUpdated()}ready(){super.ready(),this.addController(new oo(this,e=>{this._setInputElement(e),this._setFocusElement(e),this.stateTarget=e,this.ariaTarget=e})),this.addController(new _t(this.inputElement,this._labelController)),this.addEventListener("animationend",this._onAnimationEnd),this._inputField=this.shadowRoot.querySelector("[part=input-field]"),this._inputField.addEventListener("wheel",e=>{const t=this._inputField.scrollTop;this._inputField.scrollTop+=e.deltaY,t!==this._inputField.scrollTop&&(e.preventDefault(),this.__scrollPositionUpdated())}),this._updateHeight(),this.__scrollPositionUpdated()}__scrollPositionUpdated(){this._inputField.style.setProperty("--_text-area-vertical-scroll-position","0px"),this._inputField.style.setProperty("--_text-area-vertical-scroll-position",`${this._inputField.scrollTop}px`)}_onAnimationEnd(e){e.animationName.indexOf("vaadin-text-area-appear")===0&&this._updateHeight()}_valueChanged(e,t){super._valueChanged(e,t),this._updateHeight()}_updateHeight(){const e=this.inputElement,t=this._inputField;if(!e||!t)return;const s=t.scrollTop,r=this.value?this.value.length:0;if(this._oldValueLength>=r){const l=getComputedStyle(t).height,n=getComputedStyle(e).width;t.style.display="block",t.style.height=l,e.style.maxWidth=n,e.style.height="auto"}this._oldValueLength=r;const a=e.scrollHeight;a>e.clientHeight&&(e.style.height=`${a}px`),e.style.removeProperty("max-width"),t.style.removeProperty("display"),t.style.removeProperty("height"),t.scrollTop=s}checkValidity(){if(!super.checkValidity())return!1;if(!this.pattern||!this.inputElement.value)return!0;try{const e=this.inputElement.value.match(this.pattern);return e?e[0]===e.input:!1}catch{return!0}}}customElements.define(lt.is,lt);u("vaadin-message-input-text-area",p`
    :host {
      margin: 0 var(--lumo-space-s) 0 0;
    }

    :host([dir='rtl']) {
      margin: 0 0 0 var(--lumo-space-s);
    }
  `,{moduleId:"lumo-message-input-text-area"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-message-input-text-area",p`
    :host {
      align-self: stretch;
      flex-grow: 1;
    }

    .textarea-wrapper {
      min-height: 0;
    }
  `,{moduleId:"vaadin-message-input-text-area-styles"});class Ri extends lt{static get is(){return"vaadin-message-input-text-area"}static get properties(){return{ariaLabel:{type:String,observer:"__ariaLabelChanged"}}}_inputElementChanged(e){super._inputElementChanged(e),e&&(e.removeAttribute("aria-labelledby"),e.setAttribute("rows",1),e.style.minHeight="0",this.__updateAriaLabel(this.ariaLabel))}_onKeyDown(e){e.key==="Enter"&&!e.shiftKey&&(e.preventDefault(),e.stopPropagation(),this.dispatchEvent(new CustomEvent("enter"))),super._onKeyDown(e)}__updateAriaLabel(e){e?this.inputElement.setAttribute("aria-label",e):this.inputElement.removeAttribute("aria-label")}__ariaLabelChanged(e){!this.inputElement||this.__updateAriaLabel(e)}}customElements.define(Ri.is,Ri);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-message-input-button",p`
    :host {
      flex-shrink: 0;
    }
  `,{moduleId:"vaadin-message-input-button-styles"});class Di extends ss{static get is(){return"vaadin-message-input-button"}}customElements.define(Di.is,Di);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Li extends A(y(g)){static get properties(){return{value:{type:String},i18n:{type:Object,value:()=>({send:"Send",message:"Message"})},disabled:{type:Boolean,value:!1,reflectToAttribute:!0}}}static get template(){return f`
      <style>
        :host {
          align-items: flex-start;
          box-sizing: border-box;
          display: flex;
          max-height: 50vh;
          overflow: hidden;
          flex-shrink: 0;
        }

        :host([hidden]) {
          display: none !important;
        }
      </style>
      <vaadin-message-input-text-area
        disabled="[[disabled]]"
        value="{{value}}"
        placeholder="[[i18n.message]]"
        aria-label="[[i18n.message]]"
        on-enter="__submit"
      ></vaadin-message-input-text-area>
      <vaadin-message-input-button disabled="[[disabled]]" theme="primary contained" on-click="__submit"
        >[[i18n.send]]</vaadin-message-input-button
      >
    `}static get is(){return"vaadin-message-input"}__submit(){this.value!==""&&(this.dispatchEvent(new CustomEvent("submit",{detail:{value:this.value}})),this.value=""),this.shadowRoot.querySelector("vaadin-message-input-text-area").focus()}}customElements.define(Li.is,Li);u("vaadin-message-avatar",p`
    :host {
      margin-right: calc(var(--lumo-space-m) - var(--vaadin-avatar-outline-width));
      margin-top: calc(var(--lumo-space-s) - var(--vaadin-avatar-outline-width));
    }

    :host([dir='rtl']) {
      margin-left: calc(var(--lumo-space-m) - var(--vaadin-avatar-outline-width));
      margin-right: calc(var(--vaadin-avatar-outline-width) * -1);
    }
  `,{moduleId:"lumo-message-avatar"});u("vaadin-message",p`
    :host {
      color: var(--lumo-body-text-color);
      font-family: var(--lumo-font-family);
      font-size: var(--lumo-font-size-m);
      line-height: var(--lumo-line-height-m);
      padding: var(--lumo-space-s) var(--lumo-space-m);
      -moz-osx-font-smoothing: grayscale;
      -webkit-font-smoothing: antialiased;
      -webkit-text-size-adjust: 100%;
    }

    :host([focus-ring]) {
      box-shadow: inset 0 0 0 2px var(--lumo-primary-color-50pct);
    }

    [part='header'] {
      min-height: calc(var(--lumo-font-size-m) * var(--lumo-line-height-m));
    }

    [part='name'] {
      margin-right: var(--lumo-space-s);
    }

    [part='name']:empty {
      margin-right: 0;
    }

    :host([dir='rtl']) [part='name'] {
      margin-left: var(--lumo-space-s);
      margin-right: 0;
    }

    :host([dir='rtl']) [part='name']:empty {
      margin-left: 0;
    }

    [part='time'] {
      color: var(--lumo-secondary-text-color);
      font-size: var(--lumo-font-size-s);
    }
  `,{moduleId:"lumo-message"});u("vaadin-message-list",p``,{moduleId:"lumo-message-list"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-message-avatar",p`
    :host {
      --vaadin-avatar-outline-width: 0px; /* stylelint-disable-line length-zero-no-unit */
      flex-shrink: 0;
    }
  `,{moduleId:"vaadin-message-avatar-styles"});class zi extends at{static get is(){return"vaadin-message-avatar"}}customElements.define(zi.is,zi);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class dt extends O(A(y(g))){static get properties(){return{time:{type:String},userName:{type:String},userAbbr:{type:String},userImg:{type:String},userColorIndex:{type:Number}}}static get template(){return f`
      <style>
        :host {
          display: flex;
          flex-direction: row;
          outline: none;
        }

        :host([hidden]) {
          display: none !important;
        }

        [part='content'] {
          display: flex;
          flex-direction: column;
          flex-grow: 1;
        }

        [part='header'] {
          align-items: baseline;
          display: flex;
          flex-direction: row;
          flex-wrap: wrap;
        }

        [part='name'] {
          font-weight: 500;
        }

        [part='message'] {
          white-space: pre-wrap;
        }
      </style>
      <vaadin-message-avatar
        part="avatar"
        name="[[userName]]"
        abbr="[[userAbbr]]"
        img="[[userImg]]"
        color-index="[[userColorIndex]]"
        tabindex="-1"
        aria-hidden="true"
      ></vaadin-message-avatar>
      <div part="content">
        <div part="header">
          <span part="name">[[userName]]</span>
          <span part="time">[[time]]</span>
        </div>
        <div part="message"><slot></slot></div>
      </div>
    `}static get is(){return"vaadin-message"}}customElements.define(dt.is,dt);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Ni extends A(y(g)){static get is(){return"vaadin-message-list"}static get properties(){return{items:{type:Array,value:()=>[],observer:"_itemsChanged"}}}static get template(){return f`
      <style>
        :host {
          display: block;
          overflow: auto;
        }

        :host([hidden]) {
          display: none !important;
        }
      </style>
      <div part="list" role="list">
        <template is="dom-repeat" items="[[items]]">
          <vaadin-message
            time="[[item.time]]"
            user-name="[[item.userName]]"
            user-abbr="[[item.userAbbr]]"
            user-img="[[item.userImg]]"
            user-color-index="[[item.userColorIndex]]"
            theme$="[[item.theme]]"
            role="listitem"
            on-focusin="_handleFocusEvent"
            >[[item.text]]</vaadin-message
          >
        </template>
      </div>
    `}ready(){super.ready(),this.setAttribute("aria-relevant","additions"),this.setAttribute("role","log"),this.addEventListener("keydown",e=>this._onKeydown(e))}get _messages(){return Array.from(this.shadowRoot.querySelectorAll("vaadin-message"))}_itemsChanged(e,t){const s=this._getIndexOfFocusableElement();if(e&&e.length){const r=!t||e.length>t.length,a=this.scrollHeight<this.clientHeight+this.scrollTop+50;Nr.run(()=>{this._setTabIndexesByIndex(s),r&&a&&this._scrollToLastMessage()})}}_scrollToLastMessage(){this.items.length>0&&(this.scrollTop=this.scrollHeight-this.clientHeight)}_onKeydown(e){if(e.metaKey||e.ctrlKey)return;const t=e.composedPath()[0];let s=this._messages.indexOf(t);switch(e.key){case"ArrowUp":s-=1;break;case"ArrowDown":s+=1;break;case"Home":s=0;break;case"End":s=this._messages.length-1;break;default:return}s<0&&(s=this._messages.length-1),s>this._messages.length-1&&(s=0),this._focus(s),e.preventDefault()}_focus(e){this._messages[e].focus()}_handleFocusEvent(e){const t=e.composedPath().find(s=>s instanceof dt);this._setTabIndexesByMessage(t)}_setTabIndexesByIndex(e){const t=this._messages[e]||this._messages[0];this._setTabIndexesByMessage(t)}_setTabIndexesByMessage(e){this._messages.forEach(t=>{t.tabIndex=t===e?0:-1})}_getIndexOfFocusableElement(){const e=this._messages.findIndex(t=>t.tabIndex===0);return e!==-1?e:0}}customElements.define(Ni.is,Ni);u("vaadin-radio-button",p`
    :host {
      color: var(--lumo-body-text-color);
      font-size: var(--lumo-font-size-m);
      font-family: var(--lumo-font-family);
      line-height: var(--lumo-line-height-s);
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      -webkit-tap-highlight-color: transparent;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
      cursor: default;
      outline: none;
    }

    :host([has-label]) ::slotted(label) {
      padding: var(--lumo-space-xs) var(--lumo-space-s) var(--lumo-space-xs) var(--lumo-space-xs);
    }

    [part='radio'] {
      width: calc(var(--lumo-size-m) / 2);
      height: calc(var(--lumo-size-m) / 2);
      margin: var(--lumo-space-xs);
      position: relative;
      border-radius: 50%;
      background-color: var(--lumo-contrast-20pct);
      transition: transform 0.2s cubic-bezier(0.12, 0.32, 0.54, 2), background-color 0.15s;
      will-change: transform;
      line-height: 1.2;
      cursor: var(--lumo-clickable-cursor);
    }

    /* Used for activation "halo" */
    [part='radio']::before {
      /* Needed to align the radio-button nicely on the baseline */
      content: '\\2003';
      pointer-events: none;
      color: transparent;
      display: inline-block;
      width: 100%;
      height: 100%;
      border-radius: inherit;
      background-color: inherit;
      transform: scale(1.4);
      opacity: 0;
      transition: transform 0.1s, opacity 0.8s;
      will-change: transform, opacity;
    }

    /* Used for the dot */
    [part='radio']::after {
      content: '';
      pointer-events: none;
      width: 0;
      height: 0;
      border: 3px solid var(--lumo-primary-contrast-color);
      border-radius: 50%;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%) scale(0);
      transition: 0.25s transform;
      will-change: transform;
      background-clip: content-box;
    }

    :host([checked]) [part='radio'] {
      background-color: var(--lumo-primary-color);
    }

    :host([checked]) [part='radio']::after {
      transform: translate(-50%, -50%) scale(1);
    }

    :host(:not([checked]):not([disabled]):hover) [part='radio'] {
      background-color: var(--lumo-contrast-30pct);
    }

    :host([active]) [part='radio'] {
      transform: scale(0.9);
      transition-duration: 0.05s;
    }

    :host([active][checked]) [part='radio'] {
      transform: scale(1.1);
    }

    :host([active]:not([checked])) [part='radio']::before {
      transition-duration: 0.01s, 0.01s;
      transform: scale(0);
      opacity: 0.4;
    }

    :host([focus-ring]) [part='radio'] {
      box-shadow: 0 0 0 1px var(--lumo-base-color), 0 0 0 3px var(--lumo-primary-color-50pct);
    }

    :host([disabled]) {
      pointer-events: none;
      color: var(--lumo-disabled-text-color);
    }

    :host([disabled]) ::slotted(label) {
      color: inherit;
    }

    :host([disabled]) [part='radio'] {
      background-color: var(--lumo-contrast-10pct);
    }

    :host([disabled]) [part='radio']::after {
      border-color: var(--lumo-contrast-30pct);
    }

    /* RTL specific styles */
    :host([dir='rtl'][has-label]) ::slotted(label) {
      padding: var(--lumo-space-xs) var(--lumo-space-xs) var(--lumo-space-xs) var(--lumo-space-s);
    }
  `,{moduleId:"lumo-radio-button"});/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Pe extends Vr($r(ct(Hr(A(y(yt(g))))))){static get is(){return"vaadin-radio-button"}static get template(){return f`
      <style>
        :host {
          display: inline-block;
        }

        :host([hidden]) {
          display: none !important;
        }

        :host([disabled]) {
          -webkit-tap-highlight-color: transparent;
        }

        .vaadin-radio-button-container {
          display: grid;
          grid-template-columns: auto 1fr;
          align-items: baseline;
        }

        .vaadin-radio-button-wrapper {
          position: relative;
          height: 100%;
        }

        /* visually hidden */
        ::slotted(input) {
          position: absolute;
          top: 0;
          left: 0;
          right: 0;
          width: 100%;
          height: 100%;
          opacity: 0;
          cursor: inherit;
          margin: 0;
        }
      </style>
      <div class="vaadin-radio-button-container">
        <div class="vaadin-radio-button-wrapper">
          <div part="radio"></div>
          <slot name="input"></slot>
        </div>

        <slot name="label"></slot>

        <div style="display: none !important">
          <slot id="noop"></slot>
        </div>
      </div>
    `}static get properties(){return{name:{type:String,value:""}}}static get delegateAttrs(){return[...super.delegateAttrs,"name"]}constructor(){super(),this._setType("radio"),this.value="on"}ready(){super.ready(),this.addController(new es(this,e=>{this._setInputElement(e),this._setFocusElement(e),this.stateTarget=e,this.ariaTarget=e})),this.addController(new _t(this.inputElement,this._labelController)),this.addController(new Ur(this.$.noop,()=>this._labelController.node,()=>this.__warnDeprecated()))}__warnDeprecated(){console.warn(`WARNING: Since Vaadin 22, placing the label as a direct child of a <vaadin-radio-button> is deprecated.
  Please use <label slot="label"> wrapper or the label property instead.`)}}customElements.define(Pe.is,Pe);const lo=p`
  :host {
    color: var(--lumo-body-text-color);
    font-size: var(--lumo-font-size-m);
    font-family: var(--lumo-font-family);
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    -webkit-tap-highlight-color: transparent;
    padding: var(--lumo-space-xs) 0;
  }

  :host::before {
    /* Effective height of vaadin-radio-button */
    height: var(--lumo-size-s);
    box-sizing: border-box;
    display: inline-flex;
    align-items: center;
  }

  :host([theme~='vertical']) [part='group-field'] {
    display: flex;
    flex-direction: column;
  }

  :host([focused]:not([readonly])) [part='label'] {
    color: var(--lumo-primary-text-color);
  }

  :host(:hover:not([readonly]):not([focused])) [part='label'],
  :host(:hover:not([readonly])) [part='helper-text'] {
    color: var(--lumo-body-text-color);
  }

  /* Touch device adjustment */
  @media (pointer: coarse) {
    :host(:hover:not([readonly]):not([focused])) [part='label'] {
      color: var(--lumo-secondary-text-color);
    }
  }
`;u("vaadin-radio-group",[Fe,Oe,lo],{moduleId:"lumo-radio-group"});/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Vi extends de(O(Re(ut(A(y(g)))))){static get is(){return"vaadin-radio-group"}static get template(){return f`
      <style>
        :host {
          display: inline-flex;
        }

        :host::before {
          content: '\\2003';
          width: 0;
          display: inline-block;
        }

        :host([hidden]) {
          display: none !important;
        }

        .vaadin-group-field-container {
          display: flex;
          flex-direction: column;
          width: 100%;
        }

        :host(:not([has-label])) [part='label'] {
          display: none;
        }
      </style>
      <div class="vaadin-group-field-container">
        <div part="label">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true"></span>
        </div>

        <div part="group-field">
          <slot></slot>
        </div>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>
    `}static get properties(){return{value:{type:String,notify:!0,value:"",observer:"__valueChanged"},readonly:{type:Boolean,value:!1,reflectToAttribute:!0,observer:"__readonlyChanged"},_fieldName:{type:String}}}constructor(){super(),this.__registerRadioButton=this.__registerRadioButton.bind(this),this.__unregisterRadioButton=this.__unregisterRadioButton.bind(this),this.__onRadioButtonCheckedChange=this.__onRadioButtonCheckedChange.bind(this)}ready(){super.ready(),this.ariaTarget=this,this.setAttribute("role","radiogroup"),this._fieldName=`${this.localName}-${pt()}`,this._observer=new k(this,({addedNodes:e,removedNodes:t})=>{this.__filterRadioButtons(e).reverse().forEach(this.__registerRadioButton),this.__filterRadioButtons(t).forEach(this.__unregisterRadioButton)})}__filterRadioButtons(e){return e.filter(t=>t instanceof Pe)}get __radioButtons(){return this.__filterRadioButtons([...this.children])}get __selectedRadioButton(){return this.__radioButtons.find(e=>e.checked)}get isHorizontalRTL(){return this.getAttribute("dir")==="rtl"&&this._theme!=="vertical"}_onKeyDown(e){super._onKeyDown(e);const t=e.composedPath().find(s=>s instanceof Pe);["ArrowLeft","ArrowUp"].includes(e.key)&&(e.preventDefault(),this.__selectNextRadioButton(t)),["ArrowRight","ArrowDown"].includes(e.key)&&(e.preventDefault(),this.__selectPrevRadioButton(t))}_invalidChanged(e){super._invalidChanged(e),e?this.setAttribute("aria-invalid","true"):this.removeAttribute("aria-invalid")}__selectNextRadioButton(e){const t=this.__radioButtons.indexOf(e);this.__selectIncRadioButton(t,this.isHorizontalRTL?1:-1)}__selectPrevRadioButton(e){const t=this.__radioButtons.indexOf(e);this.__selectIncRadioButton(t,this.isHorizontalRTL?-1:1)}__selectIncRadioButton(e,t){const s=(this.__radioButtons.length+e+t)%this.__radioButtons.length,r=this.__radioButtons[s];r.disabled?this.__selectIncRadioButton(s,t):(r.focusElement.focus(),r.focusElement.click())}__registerRadioButton(e){e.name=this._fieldName,e.addEventListener("checked-changed",this.__onRadioButtonCheckedChange),(this.disabled||this.readonly)&&(e.disabled=!0),e.checked&&this.__selectRadioButton(e)}__unregisterRadioButton(e){e.removeEventListener("checked-changed",this.__onRadioButtonCheckedChange),e.value===this.value&&this.__selectRadioButton(null)}__onRadioButtonCheckedChange(e){e.target.checked&&this.__selectRadioButton(e.target)}__valueChanged(e,t){if(!(t===void 0&&e==="")){if(e){const s=this.__radioButtons.find(r=>r.value===e);s?(this.__selectRadioButton(s),this.toggleAttribute("has-value",!0)):console.warn(`The radio button with the value "${e}" was not found.`)}else this.__selectRadioButton(null),this.removeAttribute("has-value");t!==void 0&&this.validate()}}__readonlyChanged(e,t){!e&&t===void 0||t!==e&&this.__updateRadioButtonsDisabledProperty()}_disabledChanged(e,t){super._disabledChanged(e,t),!(!e&&t===void 0)&&t!==e&&this.__updateRadioButtonsDisabledProperty()}_shouldRemoveFocus(e){return!this.contains(e.relatedTarget)}_setFocused(e){super._setFocused(e),e||this.validate()}__selectRadioButton(e){e?this.value=e.value:this.value="",this.__radioButtons.forEach(t=>{t.checked=t===e}),this.readonly&&this.__updateRadioButtonsDisabledProperty()}__updateRadioButtonsDisabledProperty(){this.__radioButtons.forEach(e=>{this.readonly?e.disabled=e!==this.__selectedRadioButton:e.disabled=this.disabled})}}customElements.define(Vi.is,Vi);const ho=p`
  :host {
    outline: none;
  }

  :host([focus-ring]) {
    box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct);
  }
`;u("vaadin-scroller",ho,{moduleId:"lumo-scroller"});/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Js{constructor(e,t){this.host=e,this.scrollTarget=t||e,this.__boundOnScroll=this.__onScroll.bind(this)}hostConnected(){this.initialized||(this.initialized=!0,this.observe())}observe(){this.__resizeObserver=new ResizeObserver(()=>{this.__debounceOverflow=U.debounce(this.__debounceOverflow,jr,()=>{this.__updateOverflow()})}),this.__resizeObserver.observe(this.host),this.__childObserver=new k(this.host,e=>{e.addedNodes.forEach(t=>{t.nodeType===Node.ELEMENT_NODE&&this.__resizeObserver.observe(t)}),e.removedNodes.forEach(t=>{t.nodeType===Node.ELEMENT_NODE&&this.__resizeObserver.unobserve(t)}),this.__updateOverflow()}),this.scrollTarget.addEventListener("scroll",this.__boundOnScroll),this.__updateOverflow()}__onScroll(){this.__updateOverflow()}__updateOverflow(){const e=this.scrollTarget;let t="";e.scrollTop>0&&(t+=" top"),e.scrollTop<e.scrollHeight-e.clientHeight&&(t+=" bottom");const s=Math.abs(e.scrollLeft);s>0&&(t+=" start"),s<e.scrollWidth-e.clientWidth&&(t+=" end"),t=t.trim(),t.length>0&&this.host.getAttribute("overflow")!==t?this.host.setAttribute("overflow",t):t.length===0&&this.host.hasAttribute("overflow")&&this.host.removeAttribute("overflow")}}/**
 * @license
 * Copyright (c) 2020 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class $i extends O(A(yt(y(g)))){static get template(){return f`
      <style>
        :host([hidden]) {
          display: none !important;
        }

        :host {
          display: block;
          overflow: auto;
        }

        :host([scroll-direction='vertical']) {
          overflow-x: hidden;
        }

        :host([scroll-direction='horizontal']) {
          overflow-y: hidden;
        }

        :host([scroll-direction='none']) {
          overflow: hidden;
        }
      </style>

      <slot></slot>
    `}static get is(){return"vaadin-scroller"}static get properties(){return{scrollDirection:{type:String,reflectToAttribute:!0},tabindex:{type:Number,value:0,reflectToAttribute:!0}}}ready(){super.ready(),this.__overflowController=new Js(this),this.addController(this.__overflowController)}_shouldSetFocus(e){return e.target===this}}customElements.define($i.is,$i);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const co=p`
  :host(:not([theme*='align'])) ::slotted([slot='value']) {
    text-align: start;
  }

  [part='input-field'] {
    cursor: var(--lumo-clickable-cursor);
  }

  [part='input-field'] ::slotted([slot='value']) {
    font-weight: 500;
  }

  [part='input-field'] ::slotted([slot='value']:not([placeholder])) {
    color: var(--lumo-body-text-color);
  }

  :host([readonly]) [part='input-field'] ::slotted([slot='value']:not([placeholder])) {
    color: var(--lumo-secondary-text-color);
  }

  /* placeholder styles */
  [part='input-field'] ::slotted([slot='value'][placeholder]) {
    color: inherit;
    transition: opacity 0.175s 0.1s;
    opacity: 0.5;
  }

  [part='toggle-button']::before {
    content: var(--lumo-icons-dropdown);
  }

  /* Highlight the toggle button when hovering over the entire component */
  :host(:hover:not([readonly]):not([disabled])) [part='toggle-button'] {
    color: var(--lumo-contrast-80pct);
  }

  :host([theme~='small']) [part='input-field'] ::slotted([slot='value']) {
    --_lumo-selected-item-height: var(--lumo-size-s);
    --_lumo-selected-item-padding: 0;
  }
`;u("vaadin-select",[De,co],{moduleId:"lumo-select"});u("vaadin-select-value-button",p`
    :host {
      font-family: var(--lumo-font-family);
      font-size: var(--lumo-font-size-m);
      padding: 0 0.25em;
      --_lumo-selected-item-height: var(--lumo-size-m);
      --_lumo-selected-item-padding: 0.5em;
    }

    ::slotted(*) {
      min-height: var(--_lumo-selected-item-height);
      padding-top: var(--_lumo-selected-item-padding);
      padding-bottom: var(--_lumo-selected-item-padding);
    }

    ::slotted(*:hover) {
      background-color: transparent;
    }
  `,{moduleId:"lumo-select-value-button"});const uo=p`
  :host {
    --_lumo-item-selected-icon-display: block;
  }

  [part~='overlay'] {
    min-width: var(--vaadin-select-text-field-width);
  }

  /* Small viewport adjustment */
  :host([phone]) {
    top: 0 !important;
    right: 0 !important;
    bottom: var(--vaadin-overlay-viewport-bottom, 0) !important;
    left: 0 !important;
    align-items: stretch;
    justify-content: flex-end;
  }

  :host([theme~='align-left']) {
    text-align: left;
  }

  :host([theme~='align-right']) {
    text-align: right;
  }

  :host([theme~='align-center']) {
    text-align: center;
  }
`;u("vaadin-select-overlay",[Gr,uo],{moduleId:"lumo-select-overlay"});/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Hi extends Wr{static get is(){return"vaadin-select-item"}}customElements.define(Hi.is,Hi);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Ui extends Zi{static get is(){return"vaadin-select-list-box"}}customElements.define(Ui.is,Ui);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-select-overlay",p`
    :host {
      align-items: flex-start;
      justify-content: flex-start;
    }
  `,{moduleId:"vaadin-select-overlay-styles"});class ji extends mt(Me){static get is(){return"vaadin-select-overlay"}requestContentUpdate(){if(super.requestContentUpdate(),this.owner){const e=this._getMenuElement();this.owner._assignMenuElement(e)}}_getMenuElement(){return Array.from(this.children).find(e=>e.localName!=="style")}}customElements.define(ji.is,ji);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Gi extends Yr(y(g)){static get is(){return"vaadin-select-value-button"}static get template(){return f`
      <style>
        :host {
          display: inline-block;
          position: relative;
          outline: none;
          white-space: nowrap;
          -webkit-user-select: none;
          -moz-user-select: none;
          user-select: none;
          min-width: 0;
          width: 0;
        }

        ::slotted(*) {
          padding-left: 0;
          padding-right: 0;
          flex: auto;
        }

        /* placeholder styles */
        ::slotted(*:not([selected])) {
          line-height: 1;
        }

        .vaadin-button-container {
          display: inline-flex;
          align-items: center;
          justify-content: center;
          text-align: inherit;
          width: 100%;
          height: 100%;
          min-height: inherit;
          text-shadow: inherit;
        }

        [part='label'] {
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          width: 100%;
          line-height: inherit;
        }
      </style>
      <div class="vaadin-button-container">
        <span part="label">
          <slot></slot>
        </span>
      </div>
    `}}customElements.define(Gi.is,Gi);/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */u("vaadin-select",[Jr,Kr],{moduleId:"vaadin-select-styles"});class Wi extends ct(qr(de(A(y(g))))){static get is(){return"vaadin-select"}static get template(){return f`
      <style>
        ::slotted([slot='value']) {
          flex-grow: 1;
        }
      </style>

      <div class="vaadin-select-container">
        <div part="label" on-click="_onClick">
          <slot name="label"></slot>
          <span part="required-indicator" aria-hidden="true" on-click="focus"></span>
        </div>

        <vaadin-input-container
          part="input-field"
          readonly="[[readonly]]"
          disabled="[[disabled]]"
          invalid="[[invalid]]"
          theme$="[[_theme]]"
          on-click="_onClick"
        >
          <slot name="prefix" slot="prefix"></slot>
          <slot name="value"></slot>
          <div part="toggle-button" slot="suffix" aria-hidden="true" on-mousedown="_onToggleMouseDown"></div>
        </vaadin-input-container>

        <div part="helper-text">
          <slot name="helper"></slot>
        </div>

        <div part="error-message">
          <slot name="error-message"></slot>
        </div>
      </div>

      <vaadin-select-overlay
        position-target="[[_inputContainer]]"
        opened="{{opened}}"
        with-backdrop="[[_phone]]"
        phone$="[[_phone]]"
        theme$="[[_theme]]"
      ></vaadin-select-overlay>
    `}static get properties(){return{items:{type:Array,observer:"__itemsChanged"},opened:{type:Boolean,value:!1,notify:!0,reflectToAttribute:!0,observer:"_openedChanged"},renderer:Function,value:{type:String,value:"",notify:!0,observer:"_valueChanged"},name:{type:String},placeholder:{type:String},readonly:{type:Boolean,value:!1,reflectToAttribute:!0},_phone:Boolean,_phoneMediaQuery:{value:"(max-width: 420px), (max-height: 420px)"},_overlayElement:Object,_inputContainer:Object,_items:Object}}static get delegateAttrs(){return[...super.delegateAttrs,"invalid"]}static get observers(){return["_updateAriaExpanded(opened)","_updateSelectedItem(value, _items, placeholder)","_rendererChanged(renderer, _overlayElement)"]}get _valueButton(){return this._valueButtonController&&this._valueButtonController.node}constructor(){super(),this._fieldId=`${this.localName}-${pt()}`,this._boundOnKeyDown=this._onKeyDown.bind(this)}disconnectedCallback(){super.disconnectedCallback(),this.opened=!1}ready(){super.ready(),this._overlayElement=this.shadowRoot.querySelector("vaadin-select-overlay"),this._inputContainer=this.shadowRoot.querySelector('[part~="input-field"]'),this._valueButtonController=new rs(this,"value",()=>document.createElement("vaadin-select-value-button"),(e,t)=>{this._setFocusElement(t),this.ariaTarget=t,this.stateTarget=t,t.setAttribute("aria-haspopup","listbox"),t.setAttribute("aria-labelledby",`${this._labelId} ${this._fieldId}`),this._updateAriaExpanded(e.opened),t.addEventListener("keydown",this._boundOnKeyDown)}),this.addController(this._valueButtonController),this.addController(new Qr(this._phoneMediaQuery,e=>{this._phone=e})),ns(this)}requestContentUpdate(){!this._overlayElement||(this._overlayElement.requestContentUpdate(),this._menuElement&&this._menuElement.items&&this._updateSelectedItem(this.value,this._menuElement.items))}_requiredChanged(e){super._requiredChanged(e),e===!1&&this.validate()}_rendererChanged(e,t){!t||(t.setProperties({owner:this,renderer:e||this.__defaultRenderer}),this.requestContentUpdate())}__itemsChanged(e,t){(e||t)&&this.requestContentUpdate()}_assignMenuElement(e){e&&e!==this.__lastMenuElement&&(this._menuElement=e,this.__initMenuItems(e),e.addEventListener("items-changed",()=>{this.__initMenuItems(e)}),e.addEventListener("selected-changed",()=>this.__updateValueButton()),e.addEventListener("keydown",t=>this._onKeyDownInside(t),!0),e.addEventListener("click",()=>{this.__userInteraction=!0,this.opened=!1},!0),e.setAttribute("role","listbox"),this.__lastMenuElement=e)}__initMenuItems(e){e.items&&(this._items=e.items,this._items.forEach(t=>t.setAttribute("role","option")))}_valueChanged(e,t){this.toggleAttribute("has-value",Boolean(e)),t!==void 0&&this.validate()}_onClick(e){e.preventDefault(),this.opened=!this.readonly}_onToggleMouseDown(e){e.preventDefault()}_onKeyDown(e){if(!this.readonly&&!this.opened){if(/^(Enter|SpaceBar|\s|ArrowDown|Down|ArrowUp|Up)$/.test(e.key))e.preventDefault(),this.opened=!0;else if(/[\p{L}\p{Nd}]/u.test(e.key)&&e.key.length===1){const t=this._menuElement.selected,s=t!==void 0?t:-1,r=this._menuElement._searchKey(s,e.key);r>=0&&(this.__userInteraction=!0,this._updateAriaLive(!0),this._menuElement.selected=r)}}}_onKeyDownInside(e){/^(Tab)$/.test(e.key)&&(this.opened=!1)}_openedChanged(e,t){if(e){if(this._updateAriaLive(!1),!this._overlayElement||!this._menuElement||!this.focusElement||this.disabled||this.readonly){this.opened=!1;return}this._overlayElement.style.setProperty("--vaadin-select-text-field-width",`${this._inputContainer.offsetWidth}px`);const s=this.hasAttribute("focus-ring");this._openedWithFocusRing=s,s&&this.removeAttribute("focus-ring"),this._menuElement.focus()}else t&&(this.focus(),this._openedWithFocusRing&&this.setAttribute("focus-ring",""),this.validate())}_updateAriaExpanded(e){this._valueButton&&this._valueButton.setAttribute("aria-expanded",e?"true":"false")}_updateAriaLive(e){this._valueButton&&(e?this._valueButton.setAttribute("aria-live","polite"):this._valueButton.removeAttribute("aria-live"))}__attachSelectedItem(e){let t;const s=e.getAttribute("label");s?t=this.__createItemElement({label:s}):t=e.cloneNode(!0),t._sourceItem=e,this.__appendValueItemElement(t),t.selected=!0}__createItemElement(e){const t=document.createElement(e.component||"vaadin-select-item");return e.label&&(t.textContent=e.label),e.value&&(t.value=e.value),e.disabled&&(t.disabled=e.disabled),t}__appendValueItemElement(e){e.removeAttribute("tabindex"),e.removeAttribute("role"),e.setAttribute("id",this._fieldId),this._valueButton.appendChild(e)}__updateValueButton(){if(!this._valueButton)return;this._valueButton.innerHTML="";const e=this._items[this._menuElement.selected];if(this._valueButton.removeAttribute("placeholder"),e)this.__attachSelectedItem(e),this._valueChanging||(this._selectedChanging=!0,this.value=e.value||"",this.__userInteraction&&(this.opened=!1,this.dispatchEvent(new CustomEvent("change",{bubbles:!0})),this.__userInteraction=!1),delete this._selectedChanging);else if(this.placeholder){const t=this.__createItemElement({label:this.placeholder});this.__appendValueItemElement(t),this._valueButton.setAttribute("placeholder","")}}_updateSelectedItem(e,t){if(t){const s=e==null?e:e.toString();this._menuElement.selected=t.reduce((r,a,l)=>r===void 0&&a.value===s?l:r,void 0),this._selectedChanging||(this._valueChanging=!0,this.__updateValueButton(),delete this._valueChanging)}}_shouldRemoveFocus(){return!this.opened}_setFocused(e){super._setFocused(e),e||this.validate()}checkValidity(){return!this.required||this.readonly||!!this.value}__defaultRenderer(e,t){if(!this.items||this.items.length===0){e.textContent="";return}let s=e.firstElementChild;s||(s=document.createElement("vaadin-select-list-box"),e.appendChild(s)),s.textContent="",this.items.forEach(r=>{s.appendChild(this.__createItemElement(r))})}}customElements.define(Wi.is,Wi);u("vaadin-split-layout",p`
    [part='splitter'] {
      min-width: var(--lumo-space-s);
      min-height: var(--lumo-space-s);
      background-color: var(--lumo-contrast-5pct);
      transition: 0.1s background-color;
    }

    [part='handle'] {
      display: flex;
      align-items: center;
      justify-content: center;
      width: var(--lumo-size-m);
      height: var(--lumo-size-m);
    }

    [part='handle']::after {
      content: '';
      display: block;
      width: 4px;
      height: 100%;
      max-width: 100%;
      max-height: 100%;
      border-radius: var(--lumo-border-radius-s);
      background-color: var(--lumo-contrast-30pct);
      transition: 0.1s opacity, 0.1s background-color;
    }

    :host([orientation='vertical']) [part='handle']::after {
      width: 100%;
      height: 4px;
    }

    /* Hover style */
    [part='splitter']:hover [part='handle']::after {
      background-color: var(--lumo-contrast-40pct);
    }

    /* Disable hover for touch devices */
    @media (pointer: coarse) {
      [part='splitter']:hover [part='handle']::after {
        background-color: var(--lumo-contrast-30pct);
      }
    }

    /* Active style */
    [part='splitter']:active [part='handle']::after {
      background-color: var(--lumo-contrast-50pct);
    }

    /* Small/minimal */
    :host([theme~='small']) > [part='splitter'] {
      border-left: 1px solid var(--lumo-contrast-10pct);
      border-top: 1px solid var(--lumo-contrast-10pct);
    }

    :host([theme~='small']) > [part='splitter'],
    :host([theme~='minimal']) > [part='splitter'] {
      min-width: 0;
      min-height: 0;
      background-color: transparent;
    }

    :host([theme~='small']) > [part='splitter']::after,
    :host([theme~='minimal']) > [part='splitter']::after {
      content: '';
      position: absolute;
      top: -4px;
      right: -4px;
      bottom: -4px;
      left: -4px;
    }

    :host([theme~='small']) > [part='splitter'] > [part='handle']::after,
    :host([theme~='minimal']) > [part='splitter'] > [part='handle']::after {
      opacity: 0;
    }

    :host([theme~='small']) > [part='splitter']:hover > [part='handle']::after,
    :host([theme~='small']) > [part='splitter']:active > [part='handle']::after,
    :host([theme~='minimal']) > [part='splitter']:hover > [part='handle']::after,
    :host([theme~='minimal']) > [part='splitter']:active > [part='handle']::after {
      opacity: 1;
    }

    /* RTL specific styles */
    :host([theme~='small'][dir='rtl']) > [part='splitter'] {
      border-left: auto;
      border-right: 1px solid var(--lumo-contrast-10pct);
    }
  `,{moduleId:"lumo-split-layout"});/**
 * @license
 * Copyright (c) 2016 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Be extends A(y(g)){static get template(){return f`
      <style>
        :host {
          display: flex;
          overflow: hidden !important;
          transform: translateZ(0);
        }

        :host([hidden]) {
          display: none !important;
        }

        :host([orientation='vertical']) {
          flex-direction: column;
        }

        :host ::slotted(*) {
          flex: 1 1 auto;
          overflow: auto;
          -webkit-overflow-scrolling: touch;
        }

        [part='splitter'] {
          flex: none;
          position: relative;
          z-index: 1;
          overflow: visible;
          min-width: 8px;
          min-height: 8px;
        }

        :host(:not([orientation='vertical'])) > [part='splitter'] {
          cursor: ew-resize;
        }

        :host([orientation='vertical']) > [part='splitter'] {
          cursor: ns-resize;
        }

        [part='handle'] {
          width: 40px;
          height: 40px;
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate3d(-50%, -50%, 0);
        }
      </style>
      <slot id="primary" name="primary"></slot>
      <div part="splitter" id="splitter">
        <div part="handle"></div>
      </div>
      <slot id="secondary" name="secondary"></slot>
    `}static get is(){return"vaadin-split-layout"}static get properties(){return{orientation:{type:String,reflectToAttribute:!0,value:"horizontal"},_previousPrimaryPointerEvents:String,_previousSecondaryPointerEvents:String}}ready(){super.ready(),this.__observer=new k(this,t=>{this._cleanupNodes(t.removedNodes),this._processChildren()});const e=this.$.splitter;Ve(e,"track",this._onHandleTrack.bind(this)),Ve(e,"down",this._setPointerEventsNone.bind(this)),Ve(e,"up",this._restorePointerEvents.bind(this))}_cleanupNodes(e){e.forEach(t=>{t.parentElement instanceof Be||t.removeAttribute("slot")})}_processChildren(){[...this.children].forEach((e,t)=>{t===0?(this._primaryChild=e,e.setAttribute("slot","primary")):t===1?(this._secondaryChild=e,e.setAttribute("slot","secondary")):e.removeAttribute("slot")})}_setFlexBasis(e,t,s){t=Math.max(0,Math.min(t,s)),t===0&&(t=1e-6),e.style.flex=`1 1 ${t}px`}_setPointerEventsNone(e){!this._primaryChild||!this._secondaryChild||(this._previousPrimaryPointerEvents=this._primaryChild.style.pointerEvents,this._previousSecondaryPointerEvents=this._secondaryChild.style.pointerEvents,this._primaryChild.style.pointerEvents="none",this._secondaryChild.style.pointerEvents="none",e.preventDefault())}_restorePointerEvents(){!this._primaryChild||!this._secondaryChild||(this._primaryChild.style.pointerEvents=this._previousPrimaryPointerEvents,this._secondaryChild.style.pointerEvents=this._previousSecondaryPointerEvents)}_onHandleTrack(e){if(!this._primaryChild||!this._secondaryChild)return;const t=this.orientation==="vertical"?"height":"width";if(e.detail.state==="start"){this._startSize={container:this.getBoundingClientRect()[t]-this.$.splitter.getBoundingClientRect()[t],primary:this._primaryChild.getBoundingClientRect()[t],secondary:this._secondaryChild.getBoundingClientRect()[t]};return}const s=this.orientation==="vertical"?e.detail.dy:e.detail.dx,a=this.orientation!=="vertical"&&this.getAttribute("dir")==="rtl"?-s:s;this._setFlexBasis(this._primaryChild,this._startSize.primary+a,this._startSize.container),this._setFlexBasis(this._secondaryChild,this._startSize.secondary-a,this._startSize.container),e.detail.state==="end"&&(this.dispatchEvent(new CustomEvent("splitter-dragend")),delete this._startSize)}notifyResize(){console.warn("WARNING: Since Vaadin 23, notifyResize() is deprecated. The component uses a ResizeObserver internally and doesn't need to be explicitly notified of resizes.")}}customElements.define(Be.is,Be);/**
 * @license
 * Copyright (c) 2016 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */const Ks=document.createElement("template");Ks.innerHTML=`
  <style>
    @font-face {
      font-family: 'vaadin-upload-icons';
      src: url(data:application/font-woff;charset=utf-8;base64,d09GRgABAAAAAAasAAsAAAAABmAAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAABPUy8yAAABCAAAAGAAAABgDxIF5mNtYXAAAAFoAAAAVAAAAFQXVtKMZ2FzcAAAAbwAAAAIAAAACAAAABBnbHlmAAABxAAAAfQAAAH0bBJxYWhlYWQAAAO4AAAANgAAADYPD267aGhlYQAAA/AAAAAkAAAAJAfCA8tobXR4AAAEFAAAACgAAAAoHgAAx2xvY2EAAAQ8AAAAFgAAABYCSgHsbWF4cAAABFQAAAAgAAAAIAAOADVuYW1lAAAEdAAAAhYAAAIWmmcHf3Bvc3QAAAaMAAAAIAAAACAAAwAAAAMDtwGQAAUAAAKZAswAAACPApkCzAAAAesAMwEJAAAAAAAAAAAAAAAAAAAAARAAAAAAAAAAAAAAAAAAAAAAQAAA6QUDwP/AAEADwABAAAAAAQAAAAAAAAAAAAAAIAAAAAAAAwAAAAMAAAAcAAEAAwAAABwAAwABAAAAHAAEADgAAAAKAAgAAgACAAEAIOkF//3//wAAAAAAIOkA//3//wAB/+MXBAADAAEAAAAAAAAAAAAAAAEAAf//AA8AAQAAAAAAAAAAAAIAADc5AQAAAAABAAAAAAAAAAAAAgAANzkBAAAAAAEAAAAAAAAAAAACAAA3OQEAAAAAAgAA/8AEAAPAABkAMgAAEz4DMzIeAhczLgMjIg4CBycRIScFIRcOAyMiLgInIx4DMzI+AjcXphZGWmo6SH9kQwyADFiGrmJIhXJbIEYBAFoDWv76YBZGXGw8Rn5lRQyADFmIrWBIhHReIkYCWjJVPSIyVnVDXqN5RiVEYTxG/wBa2loyVT0iMlZ1Q16jeUYnRWE5RgAAAAABAIAAAAOAA4AAAgAAExEBgAMAA4D8gAHAAAAAAwAAAAAEAAOAAAIADgASAAAJASElIiY1NDYzMhYVFAYnETMRAgD+AAQA/gAdIyMdHSMjXYADgPyAgCMdHSMjHR0jwAEA/wAAAQANADMD5gNaAAUAACUBNwUBFwHT/jptATMBppMzAU2a4AIgdAAAAAEAOv/6A8YDhgALAAABJwkBBwkBFwkBNwEDxoz+xv7GjAFA/sCMAToBOoz+wAL6jP7AAUCM/sb+xowBQP7AjAE6AAAAAwAA/8AEAAPAAAcACwASAAABFSE1IREhEQEjNTMJAjMRIRECwP6A/sAEAP0AgIACQP7A/sDAAQABQICA/oABgP8AgAHAAUD+wP6AAYAAAAABAAAAAQAAdhiEdV8PPPUACwQAAAAAANX4FR8AAAAA1fgVHwAA/8AEAAPAAAAACAACAAAAAAAAAAEAAAPA/8AAAAQAAAAAAAQAAAEAAAAAAAAAAAAAAAAAAAAKBAAAAAAAAAAAAAAAAgAAAAQAAAAEAACABAAAAAQAAA0EAAA6BAAAAAAAAAAACgAUAB4AagB4AJwAsADSAPoAAAABAAAACgAzAAMAAAAAAAIAAAAAAAAAAAAAAAAAAAAAAAAADgCuAAEAAAAAAAEAEwAAAAEAAAAAAAIABwDMAAEAAAAAAAMAEwBaAAEAAAAAAAQAEwDhAAEAAAAAAAUACwA5AAEAAAAAAAYAEwCTAAEAAAAAAAoAGgEaAAMAAQQJAAEAJgATAAMAAQQJAAIADgDTAAMAAQQJAAMAJgBtAAMAAQQJAAQAJgD0AAMAAQQJAAUAFgBEAAMAAQQJAAYAJgCmAAMAAQQJAAoANAE0dmFhZGluLXVwbG9hZC1pY29ucwB2AGEAYQBkAGkAbgAtAHUAcABsAG8AYQBkAC0AaQBjAG8AbgBzVmVyc2lvbiAxLjAAVgBlAHIAcwBpAG8AbgAgADEALgAwdmFhZGluLXVwbG9hZC1pY29ucwB2AGEAYQBkAGkAbgAtAHUAcABsAG8AYQBkAC0AaQBjAG8AbgBzdmFhZGluLXVwbG9hZC1pY29ucwB2AGEAYQBkAGkAbgAtAHUAcABsAG8AYQBkAC0AaQBjAG8AbgBzUmVndWxhcgBSAGUAZwB1AGwAYQBydmFhZGluLXVwbG9hZC1pY29ucwB2AGEAYQBkAGkAbgAtAHUAcABsAG8AYQBkAC0AaQBjAG8AbgBzRm9udCBnZW5lcmF0ZWQgYnkgSWNvTW9vbi4ARgBvAG4AdAAgAGcAZQBuAGUAcgBhAHQAZQBkACAAYgB5ACAASQBjAG8ATQBvAG8AbgAuAAAAAwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==) format('woff');
      font-weight: normal;
      font-style: normal;
    }
  </style>
`;document.head.appendChild(Ks.content);/**
 * @license
 * Copyright (c) 2016 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Yi extends O(y(g)){static get template(){return f`
      <style>
        :host {
          display: block;
        }

        [hidden] {
          display: none;
        }

        [part='row'] {
          list-style-type: none;
        }

        button {
          background: transparent;
          padding: 0;
          border: none;
          box-shadow: none;
        }
      </style>

      <div part="row">
        <div part="info">
          <div part="done-icon" hidden$="[[!file.complete]]" aria-hidden="true"></div>
          <div part="warning-icon" hidden$="[[!file.error]]" aria-hidden="true"></div>

          <div part="meta">
            <div part="name" id="name">[[file.name]]</div>
            <div part="status" hidden$="[[!file.status]]" id="status">[[file.status]]</div>
            <div part="error" id="error" hidden$="[[!file.error]]">[[file.error]]</div>
          </div>
        </div>
        <div part="commands">
          <button
            type="button"
            part="start-button"
            file-event="file-start"
            on-click="_fireFileEvent"
            hidden$="[[!file.held]]"
            aria-label$="[[i18n.file.start]]"
            aria-describedby="name"
          ></button>
          <button
            type="button"
            part="retry-button"
            file-event="file-retry"
            on-click="_fireFileEvent"
            hidden$="[[!file.error]]"
            aria-label$="[[i18n.file.retry]]"
            aria-describedby="name"
          ></button>
          <button
            type="button"
            part="remove-button"
            file-event="file-abort"
            on-click="_fireFileEvent"
            aria-label$="[[i18n.file.remove]]"
            aria-describedby="name"
          ></button>
        </div>
      </div>

      <vaadin-progress-bar
        part="progress"
        id="progress"
        value$="[[_formatProgressValue(file.progress)]]"
        error$="[[file.error]]"
        indeterminate$="[[file.indeterminate]]"
        uploading$="[[file.uploading]]"
        complete$="[[file.complete]]"
      ></vaadin-progress-bar>
    `}static get is(){return"vaadin-upload-file"}static get properties(){return{file:Object,i18n:Object,tabindex:{type:Number,value:0,reflectToAttribute:!0}}}static get observers(){return["_fileAborted(file.abort)",'_toggleHostAttribute(file.error, "error")','_toggleHostAttribute(file.indeterminate, "indeterminate")','_toggleHostAttribute(file.uploading, "uploading")','_toggleHostAttribute(file.complete, "complete")']}ready(){super.ready(),this.shadowRoot.addEventListener("focusin",e=>{e.composedPath()[0].getAttribute("part").endsWith("button")&&this._setFocused(!1)}),this.shadowRoot.addEventListener("focusout",e=>{e.relatedTarget===this&&this._setFocused(!0)})}_shouldSetFocus(e){return e.composedPath()[0]===this}_fileAborted(e){e&&this._remove()}_remove(){this.dispatchEvent(new CustomEvent("file-remove",{detail:{file:this.file},bubbles:!0,composed:!0}))}_formatProgressValue(e){return e/100}_fireFileEvent(e){return e.preventDefault(),this.dispatchEvent(new CustomEvent(e.target.getAttribute("file-event"),{detail:{file:this.file},bubbles:!0,composed:!0}))}_toggleHostAttribute(e,t){const s=Boolean(e);this.hasAttribute(t)!==s&&(s?this.setAttribute(t,""):this.removeAttribute(t))}}customElements.define(Yi.is,Yi);u("vaadin-upload",p`
    :host {
      line-height: var(--lumo-line-height-m);
    }

    :host(:not([nodrop])) {
      overflow: hidden;
      border: 1px dashed var(--lumo-contrast-20pct);
      border-radius: var(--lumo-border-radius-l);
      padding: var(--lumo-space-m);
      transition: background-color 0.6s, border-color 0.6s;
    }

    [part='primary-buttons'] > * {
      display: inline-block;
      white-space: nowrap;
    }

    [part='drop-label'] {
      display: inline-block;
      white-space: normal;
      padding: 0 var(--lumo-space-s);
      color: var(--lumo-secondary-text-color);
      font-family: var(--lumo-font-family);
    }

    :host([dragover-valid]) {
      border-color: var(--lumo-primary-color-50pct);
      background: var(--lumo-primary-color-10pct);
      transition: background-color 0.1s, border-color 0.1s;
    }

    :host([dragover-valid]) [part='drop-label'] {
      color: var(--lumo-primary-text-color);
    }

    :host([max-files-reached]) [part='drop-label'] {
      color: var(--lumo-disabled-text-color);
    }

    [part='drop-label-icon'] {
      display: inline-block;
    }

    [part='drop-label-icon']::before {
      content: var(--lumo-icons-upload);
      font-family: lumo-icons;
      font-size: var(--lumo-icon-size-m);
      line-height: 1;
      vertical-align: -0.25em;
    }

    [part='file-list'] > *:not(:first-child) > * {
      border-top: 1px solid var(--lumo-contrast-10pct);
    }
  `,{moduleId:"lumo-upload"});const po=p`
  :host {
    padding: var(--lumo-space-s) 0;
    outline: none;
  }

  :host([focus-ring]) [part='row'] {
    border-radius: var(--lumo-border-radius-s);
    box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct);
  }

  [part='row'] {
    display: flex;
    align-items: baseline;
    justify-content: space-between;
  }

  [part='status'],
  [part='error'] {
    color: var(--lumo-secondary-text-color);
    font-size: var(--lumo-font-size-s);
  }

  [part='info'] {
    display: flex;
    align-items: baseline;
    flex: auto;
  }

  [part='meta'] {
    width: 0.001px;
    flex: 1 1 auto;
  }

  [part='name'] {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  [part='commands'] {
    display: flex;
    align-items: baseline;
    flex: none;
  }

  [part$='icon'] {
    margin-right: var(--lumo-space-xs);
    font-size: var(--lumo-icon-size-m);
    font-family: 'lumo-icons';
    line-height: 1;
  }

  /* When both icons are hidden, let us keep space for one */
  [part='done-icon'][hidden] + [part='warning-icon'][hidden] {
    display: block !important;
    visibility: hidden;
  }

  [part$='button'] {
    flex: none;
    margin-left: var(--lumo-space-xs);
    cursor: var(--lumo-clickable-cursor);
  }

  [part$='button']:focus {
    outline: none;
    border-radius: var(--lumo-border-radius-s);
    box-shadow: 0 0 0 2px var(--lumo-primary-color-50pct);
  }

  [part$='icon']::before,
  [part$='button']::before {
    vertical-align: -0.25em;
  }

  [part='done-icon']::before {
    content: var(--lumo-icons-checkmark);
    color: var(--lumo-primary-text-color);
  }

  [part='warning-icon']::before {
    content: var(--lumo-icons-error);
    color: var(--lumo-error-text-color);
  }

  [part='start-button']::before {
    content: var(--lumo-icons-play);
  }

  [part='retry-button']::before {
    content: var(--lumo-icons-reload);
  }

  [part='remove-button']::before {
    content: var(--lumo-icons-cross);
  }

  [part='error'] {
    color: var(--lumo-error-text-color);
  }

  [part='progress'] {
    width: auto;
    margin-left: calc(var(--lumo-icon-size-m) + var(--lumo-space-xs));
    margin-right: calc(var(--lumo-icon-size-m) + var(--lumo-space-xs));
  }

  [part='progress'][complete],
  [part='progress'][error] {
    display: none;
  }
`;u("vaadin-upload-file",[Zr,po],{moduleId:"lumo-upload-file"});/**
 * @license
 * Copyright (c) 2016 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class qi extends A(y(g)){static get template(){return f`
      <style>
        :host {
          display: block;
          position: relative;
          box-sizing: border-box;
        }

        :host([hidden]) {
          display: none !important;
        }

        [hidden] {
          display: none !important;
        }

        [part='file-list'] {
          padding: 0;
          margin: 0;
          list-style-type: none;
        }
      </style>

      <div part="primary-buttons">
        <div id="addFiles" on-touchend="_onAddFilesTouchEnd" on-click="_onAddFilesClick">
          <slot name="add-button">
            <vaadin-button part="upload-button" id="addButton" disabled="[[maxFilesReached]]">
              [[_i18nPlural(maxFiles, i18n.addFiles, i18n.addFiles.*)]]
            </vaadin-button>
          </slot>
        </div>
        <div part="drop-label" hidden$="[[nodrop]]" id="dropLabelContainer" aria-hidden="true">
          <slot name="drop-label-icon">
            <div part="drop-label-icon"></div>
          </slot>
          <slot name="drop-label" id="dropLabel"> [[_i18nPlural(maxFiles, i18n.dropFiles, i18n.dropFiles.*)]]</slot>
        </div>
      </div>
      <slot name="file-list">
        <ul id="fileList" part="file-list">
          <template is="dom-repeat" items="[[files]]" as="file">
            <li>
              <vaadin-upload-file file="[[file]]" i18n="[[i18n]]"></vaadin-upload-file>
            </li>
          </template>
        </ul>
      </slot>
      <slot></slot>
      <input
        type="file"
        id="fileInput"
        hidden
        on-change="_onFileInputChange"
        accept$="{{accept}}"
        multiple$="[[_isMultiple(maxFiles)]]"
        capture$="[[capture]]"
      />
    `}static get is(){return"vaadin-upload"}static get properties(){return{nodrop:{type:Boolean,reflectToAttribute:!0,value:Xr},target:{type:String,value:""},method:{type:String,value:"POST"},headers:{type:Object,value:{}},timeout:{type:Number,value:0},_dragover:{type:Boolean,value:!1,observer:"_dragoverChanged"},files:{type:Array,notify:!0,value:()=>[]},maxFiles:{type:Number,value:1/0},maxFilesReached:{type:Boolean,value:!1,notify:!0,readOnly:!0,reflectToAttribute:!0,computed:"_maxFilesAdded(maxFiles, files.length)"},accept:{type:String,value:""},maxFileSize:{type:Number,value:1/0},_dragoverValid:{type:Boolean,value:!1,observer:"_dragoverValidChanged"},formDataName:{type:String,value:"file"},noAuto:{type:Boolean,value:!1},withCredentials:{type:Boolean,value:!1},capture:String,i18n:{type:Object,value(){return{dropFiles:{one:"Drop file here",many:"Drop files here"},addFiles:{one:"Upload File...",many:"Upload Files..."},error:{tooManyFiles:"Too Many Files.",fileIsTooBig:"File is Too Big.",incorrectFileType:"Incorrect File Type."},uploading:{status:{connecting:"Connecting...",stalled:"Stalled",processing:"Processing File...",held:"Queued"},remainingTime:{prefix:"remaining time: ",unknown:"unknown remaining time"},error:{serverUnavailable:"Upload failed, please try again later",unexpectedServerError:"Upload failed due to server error",forbidden:"Upload forbidden"}},file:{retry:"Retry",start:"Start",remove:"Remove"},units:{size:["B","kB","MB","GB","TB","PB","EB","ZB","YB"]}}}}}}ready(){super.ready(),this.addEventListener("dragover",this._onDragover.bind(this)),this.addEventListener("dragleave",this._onDragleave.bind(this)),this.addEventListener("drop",this._onDrop.bind(this)),this.addEventListener("file-retry",this._onFileRetry.bind(this)),this.addEventListener("file-abort",this._onFileAbort.bind(this)),this.addEventListener("file-remove",this._onFileRemove.bind(this)),this.addEventListener("file-start",this._onFileStart.bind(this)),this.addEventListener("file-reject",this._onFileReject.bind(this)),this.addEventListener("upload-start",this._onUploadStart.bind(this)),this.addEventListener("upload-success",this._onUploadSuccess.bind(this)),this.addEventListener("upload-error",this._onUploadError.bind(this))}_formatSize(e){if(typeof this.i18n.formatSize=="function")return this.i18n.formatSize(e);const t=this.i18n.units.sizeBase||1e3,s=~~(Math.log(e)/Math.log(t)),r=Math.max(0,Math.min(3,s-1));return`${parseFloat((e/t**s).toFixed(r))} ${this.i18n.units.size[s]}`}_splitTimeByUnits(e){const t=[60,60,24,1/0],s=[0];for(let r=0;r<t.length&&e>0;r++)s[r]=e%t[r],e=Math.floor(e/t[r]);return s}_formatTime(e,t){if(typeof this.i18n.formatTime=="function")return this.i18n.formatTime(e,t);for(;t.length<3;)t.push(0);return t.reverse().map(s=>(s<10?"0":"")+s).join(":")}_formatFileProgress(e){const t=e.loaded>0?this.i18n.uploading.remainingTime.prefix+e.remainingStr:this.i18n.uploading.remainingTime.unknown;return`${e.totalStr}: ${e.progress}% (${t})`}_maxFilesAdded(e,t){return e>=0&&t>=e}_onDragover(e){e.preventDefault(),!this.nodrop&&!this._dragover&&(this._dragoverValid=!this.maxFilesReached,this._dragover=!0),e.dataTransfer.dropEffect=!this._dragoverValid||this.nodrop?"none":"copy"}_onDragleave(e){e.preventDefault(),this._dragover&&!this.nodrop&&(this._dragover=this._dragoverValid=!1)}_onDrop(e){this.nodrop||(e.preventDefault(),this._dragover=this._dragoverValid=!1,this._addFiles(e.dataTransfer.files))}_createXhr(){return new XMLHttpRequest}_configureXhr(e){if(typeof this.headers=="string")try{this.headers=JSON.parse(this.headers)}catch{this.headers=void 0}Object.entries(this.headers).forEach(([t,s])=>{e.setRequestHeader(t,s)}),this.timeout&&(e.timeout=this.timeout),e.withCredentials=this.withCredentials}_setStatus(e,t,s,r){e.elapsed=r,e.elapsedStr=this._formatTime(e.elapsed,this._splitTimeByUnits(e.elapsed)),e.remaining=Math.ceil(r*(t/s-1)),e.remainingStr=this._formatTime(e.remaining,this._splitTimeByUnits(e.remaining)),e.speed=~~(t/r/1024),e.totalStr=this._formatSize(t),e.loadedStr=this._formatSize(s),e.status=this._formatFileProgress(e)}uploadFiles(e){e&&!Array.isArray(e)&&(e=[e]),e=e||this.files,e=e.filter(t=>!t.complete),Array.prototype.forEach.call(e,this._uploadFile.bind(this))}_uploadFile(e){if(e.uploading)return;const t=Date.now(),s=e.xhr=this._createXhr();let r,a;s.upload.onprogress=d=>{clearTimeout(r),a=Date.now();const h=(a-t)/1e3,c=d.loaded,m=d.total,_=~~(c/m*100);e.loaded=c,e.progress=_,e.indeterminate=c<=0||c>=m,e.error?e.indeterminate=e.status=void 0:e.abort||(_<100?(this._setStatus(e,m,c,h),r=setTimeout(()=>{e.status=this.i18n.uploading.status.stalled,this._notifyFileChanges(e)},2e3)):(e.loadedStr=e.totalStr,e.status=this.i18n.uploading.status.processing)),this._notifyFileChanges(e),this.dispatchEvent(new CustomEvent("upload-progress",{detail:{file:e,xhr:s}}))},s.onreadystatechange=()=>{if(s.readyState===4){if(clearTimeout(r),e.indeterminate=e.uploading=!1,e.abort){this._notifyFileChanges(e);return}if(e.status="",!this.dispatchEvent(new CustomEvent("upload-response",{detail:{file:e,xhr:s},cancelable:!0})))return;s.status===0?e.error=this.i18n.uploading.error.serverUnavailable:s.status>=500?e.error=this.i18n.uploading.error.unexpectedServerError:s.status>=400&&(e.error=this.i18n.uploading.error.forbidden),e.complete=!e.error,this.dispatchEvent(new CustomEvent(`upload-${e.error?"error":"success"}`,{detail:{file:e,xhr:s}})),this._notifyFileChanges(e)}};const l=new FormData;if(e.uploadTarget=e.uploadTarget||this.target||"",e.formDataName=this.formDataName,!this.dispatchEvent(new CustomEvent("upload-before",{detail:{file:e,xhr:s},cancelable:!0})))return;l.append(e.formDataName,e,e.name),s.open(this.method,e.uploadTarget,!0),this._configureXhr(s),e.status=this.i18n.uploading.status.connecting,e.uploading=e.indeterminate=!0,e.complete=e.abort=e.error=e.held=!1,s.upload.onloadstart=()=>{this.dispatchEvent(new CustomEvent("upload-start",{detail:{file:e,xhr:s}})),this._notifyFileChanges(e)},this.dispatchEvent(new CustomEvent("upload-request",{detail:{file:e,xhr:s,formData:l},cancelable:!0}))&&s.send(l)}_retryFileUpload(e){this.dispatchEvent(new CustomEvent("upload-retry",{detail:{file:e,xhr:e.xhr},cancelable:!0}))&&this._uploadFile(e)}_abortFileUpload(e){this.dispatchEvent(new CustomEvent("upload-abort",{detail:{file:e,xhr:e.xhr},cancelable:!0}))&&(e.abort=!0,e.xhr&&e.xhr.abort(),this._notifyFileChanges(e))}_notifyFileChanges(e){const t=`files.${this.files.indexOf(e)}.`;Object.keys(e).forEach(s=>{this.notifyPath(t+s,e[s])})}_addFiles(e){Array.prototype.forEach.call(e,this._addFile.bind(this))}_addFile(e){if(this.maxFilesReached){this.dispatchEvent(new CustomEvent("file-reject",{detail:{file:e,error:this.i18n.error.tooManyFiles}}));return}if(this.maxFileSize>=0&&e.size>this.maxFileSize){this.dispatchEvent(new CustomEvent("file-reject",{detail:{file:e,error:this.i18n.error.fileIsTooBig}}));return}const t=e.name.match(/\.[^.]*$|$/)[0],s=this.accept.replace(/[+.]/g,"\\$&"),r=new RegExp(`^(${s.replace(/[, ]+/g,"|").replace(/\/\*/g,"/.*")})$`,"i");if(this.accept&&!(r.test(e.type)||r.test(t))){this.dispatchEvent(new CustomEvent("file-reject",{detail:{file:e,error:this.i18n.error.incorrectFileType}}));return}e.loaded=0,e.held=!0,e.status=this.i18n.uploading.status.held,this.files=[e,...this.files],this.noAuto||this._uploadFile(e)}_removeFile(e){this.files.indexOf(e)>-1&&(this.files=this.files.filter(t=>t!==e))}_onAddFilesTouchEnd(e){e.preventDefault(),this._onAddFilesClick(e)}_onAddFilesClick(e){this.maxFilesReached||(e.stopPropagation(),this.$.fileInput.value="",this.$.fileInput.click())}_onFileInputChange(e){this._addFiles(e.target.files)}_onFileStart(e){this._uploadFile(e.detail.file)}_onFileRetry(e){this._retryFileUpload(e.detail.file)}_onFileAbort(e){this._abortFileUpload(e.detail.file)}_onFileRemove(e){this._removeFile(e.detail.file)}_onFileReject(e){V(`${e.detail.file.name}: ${e.detail.file.error}`,{mode:"alert"})}_onUploadStart(e){V(`${e.detail.file.name}: 0%`,{mode:"alert"})}_onUploadSuccess(e){V(`${e.detail.file.name}: 100%`,{mode:"alert"})}_onUploadError(e){V(`${e.detail.file.name}: ${e.detail.file.error}`,{mode:"alert"})}_dragoverChanged(e){e?this.setAttribute("dragover",e):this.removeAttribute("dragover")}_dragoverValidChanged(e){e?this.setAttribute("dragover-valid",e):this.removeAttribute("dragover-valid")}_i18nPlural(e,t){return e===1?t.one:t.many}_isMultiple(e){return e!==1}}customElements.define(qi.is,qi);/**
 * @license
 * Copyright (c) 2021 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */class Qi extends A(yt(y(g))){static get template(){return f`
      <style>
        :host {
          display: block;
          height: 400px;
          overflow: auto;
          flex: auto;
          align-self: stretch;
        }

        :host([hidden]) {
          display: none !important;
        }

        :host(:not([grid])) #items > ::slotted(*) {
          width: 100%;
        }
      </style>

      <div id="items">
        <slot></slot>
      </div>
    `}static get is(){return"vaadin-virtual-list"}static get properties(){return{items:{type:Array},renderer:Function,__virtualizer:Object}}static get observers(){return["__itemsOrRendererChanged(items, renderer, __virtualizer)"]}ready(){super.ready(),this.__virtualizer=new en({createElements:this.__createElements,updateElement:this.__updateElement.bind(this),elementsContainer:this,scrollTarget:this,scrollContainer:this.shadowRoot.querySelector("#items")}),this.__overflowController=new Js(this),this.addController(this.__overflowController),ns(this)}scrollToIndex(e){this.__virtualizer.scrollToIndex(e)}__createElements(e){return[...Array(e)].map(()=>document.createElement("div"))}__updateElement(e,t){e.__renderer!==this.renderer&&(e.__renderer=this.renderer,this.__clearRenderTargetContent(e)),this.renderer&&this.renderer(e,this,{item:this.items[t],index:t})}__clearRenderTargetContent(e){e.innerHTML="",delete e._$litPart$}__itemsOrRendererChanged(e,t,s){const r=this.childElementCount>0;(t||r)&&s&&(s.size=(e||[]).length,s.update())}get firstVisibleIndex(){return this.__virtualizer.firstVisibleIndex}get lastVisibleIndex(){return this.__virtualizer.lastVisibleIndex}requestContentUpdate(){this.__virtualizer&&this.__virtualizer.update()}}customElements.define(Qi.is,Qi);const yo=function(i,e=!1){const t=document.createElement("template");t.innerHTML=i,document.head[e?"insertBefore":"appendChild"](t.content,document.head.firstChild)};export{yo as addCssBlock};
