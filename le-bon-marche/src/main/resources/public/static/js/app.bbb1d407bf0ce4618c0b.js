webpackJsonp([1],Array(33).concat([function(e,t,o){"use strict";var n=o(2),r=o(127),s=o(114),i=(o.n(s),o(118)),a=o.n(i);n.default.use(r.a),t.a=new r.a({routes:[{path:"/",redirect:"/main"},{path:"/main",name:"main",component:a.a}]})},function(e,t){},function(e,t,o){function n(e){o(103)}var r=o(4)(o(75),o(120),n,null,null);e.exports=r.exports},,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(113),r=o.n(n);t.default={name:"app",components:{"shared-header":r.a},created:function(){var e=this;"undefined"!=typeof Storage||alert("Not support local/session storage"),"WebSocket"in window||alert("Not support websocket"),this.$root.websocket.onopen=function(t){e.$root.websocket.send("Client side websocket: open")},this.$root.websocket.onclose=function(){e.$root.websocket.send("Client side websocket: close")},this.$root.websocket.onerror=function(){e.$root.websocket.send("Client side websocket: error"),alert("websocket error!")},this.$root.websocket.onmessage=function(t){e.$root.websocket.send("Client side websocket: Message received");var o=JSON.parse(t.data);"oauth"===o.event?(e.$root.websocket.send("Websocket event = "+o.event),e.$root.bus.$emit("full-screen-loading",!0,"Loading..."),e.$root.websocket.send("Info provider: "+o.user.source),e.$root.bus.$emit("signin-show",!1),e.$root.bus.$emit("visit-method-show",!0,o.user),e.$root.websocket.send("Visit method dialog is visible"),e.$root.bus.$emit("full-screen-loading",!1,"")):alert("Websocket error! Event = "+o.event)}}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(6);o.n(n);t.default={name:"carousel",props:["content"],components:{"el-carousel":n.Carousel,"el-carousel-item":n.CarouselItem}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(6);o.n(n);t.default={name:"header",components:{"el-dropdown":n.Dropdown,"el-dropdown-menu":n.DropdownMenu,"el-dropdown-item":n.DropdownItem},data:function(){return{signedIn:!1,username:"",role:""}},methods:{backToHome:function(){this.$router.push("/main")},signinClick:function(){this.$root.bus.$emit("signin-show",!0)},signupClick:function(){this.$root.bus.$emit("signup-show",!0)},handleCommand:function(e){if("signout"===e)return this.signedIn=!1,this.$router.push("/main"),this.$message({message:"Signed out",type:"success"}),void localStorage.clear();this.$router.push("/"+e)}},created:function(){var e=this;this.$root.bus.$on("signed-in",function(t,o){e.signedIn=t,e.username=o.username,e.role=o.role})}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"hello",data:function(){return{msg:"Welcome to Your Vue.js App"}}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(6),r=(o.n(n),o(9)),s=o.n(r);t.default={name:"signin",components:{"el-dialog":n.Dialog},data:function(){return{visible:!1,signinForm:{username:"",password:""},rules:{username:[{validator:function(e,t,o){""===t?o(new Error("Username can't be empty")):o()},trigger:"blur"}],password:[{validator:function(e,t,o){""===t?o(new Error("Password can't be empty")):o()},trigger:"blur"}]},thirdPartyLogo:[{img:"github"},{img:"wechat"},{img:"facebook"},{img:"weibo"},{img:"linkedin"}]}},methods:{signinHandler:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return!1;s.a.post("/signin",{username:t.signinForm.username,password:t.signinForm.password}).then(function(e){if(0!=e.data.code)return void alert(e.data.status);t.visible=!1,t.$root.bus.$emit("signed-in",!0,e.data.user),localStorage.setItem("access-token",e.data.accessToken),t.$message({message:"Welcome "+e.data.user.username,type:"success"}),t.$root.bus.$emit("clear-signin-form")}).catch(function(e){alert("Signin error! "+e)})})},thirdPartyLoginHandler:function(e){var t=this;if("github"!=e)return void alert("Only Github is supported now.");this.$root.bus.$emit("full-screen-loading",!0,"Getting user info from "+e),s.a.post("/oauth?provider="+e).then(function(e){if(0!=e.data.code)return t.$root.bus.$emit("full-screen-loading",!1,""),void alert(e.data.status);window.open(e.data.status)}).catch(function(e){alert("OAuth error! "+e),t.$root.bus.$emit("full-screen-loading",!1,"")})},signinDialogCloseHandler:function(){this.$root.bus.$emit("clear-signin-form")}},created:function(){var e=this;this.$root.bus.$on("clear-signin-form",function(){e.signinForm={username:"",password:""}}),this.$root.bus.$on("signin-show",function(t){e.visible=t})}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(6),r=(o.n(n),o(9)),s=o.n(r);t.default={name:"signup",props:["content"],components:{"el-dialog":n.Dialog,"el-cascader":n.Cascader,"el-radio":n.Radio},data:function(){var e=this;return{visible:!1,signupForm:{username:"",password:"",password2:"",email:"",location:"",role:"buyer",source:"local"},rules:{username:[{validator:function(t,o,n){o?s.a.post("/username",{username:e.signupForm.username}).then(function(e){0!=e.data.code?n(new Error(e.data.status)):n()}).catch(function(e){alert("Username check error! "+e),n()}):n(new Error("Please input your username"))},trigger:"blur"}],password:[{validator:function(t,o,n){o?(""!==e.signupForm.password2&&e.$refs.signupForm.validateField("password2"),n()):n(new Error("Please input your password"))},trigger:"blur"}],password2:[{validator:function(t,o,n){o?o!==e.signupForm.password?n(new Error("Inconsistent password")):n():n(new Error("Please repeat your password"))},trigger:"blur"}],email:[{validator:function(e,t,o){t?o():o(new Error("Please input your email"))},trigger:"blur"},{type:"email",message:"Invalid email",trigger:"blur"}],location:[{validator:function(e,t,o){t?o():o(new Error("Please input your location"))},trigger:"blur"}],role:[{validator:function(e,t,o){t?o():o(new Error("Please select a role"))},trigger:"change"}]}}},methods:{signupHandler:function(e){var t=this,o={username:this.signupForm.username,password:this.signupForm.password,email:this.signupForm.email,location:this.signupForm.location,role:this.signupForm.role,source:this.signupForm.source};this.$refs[e].validate(function(e){if(!e)return!1;s.a.post("/signup",o).then(function(e){if(0!=e.data.code)return void alert(e.data.status);t.visible=!1,t.$root.bus.$emit("signed-in",!0,e.data.user),localStorage.setItem("access-token",e.data.accessToken),t.$message({message:"Welcome "+e.data.user.username,type:"success"}),t.$root.bus.$emit("clear-signup-form")}).catch(function(e){alert("Signup error! "+e)})})},signupDialogCloseHandler:function(){this.$root.bus.$emit("clear-signup-form")}},created:function(){var e=this;this.$root.bus.$on("clear-signup-form",function(){e.signupForm={username:"",password:"",password2:"",email:"",location:"",role:"buyer",source:"local"}}),this.$root.bus.$on("signup-show",function(t){e.visible=t}),this.$root.bus.$on("auto-fill-signup-form",function(t){e.signupForm=t})}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(6),r=(o.n(n),o(9)),s=o.n(r);t.default={name:"visit-method",components:{"el-dialog":n.Dialog},data:function(){return{visible:!1,user:{username:"",email:"",location:"",role:""}}},methods:{visitorHandler:function(){var e=this;this.visible=!1,s.a.post("/visitor").then(function(t){if(0!=t.data.code)return void alert(t.data.status);e.$root.bus.$emit("signed-in",!0,t.data.user),localStorage.setItem("access-token",t.data.accessToken),e.$message({message:"Welcome "+t.data.user.username,type:"success"})}).catch(function(e){alert("Post /visitor error! "+e)})},signupHandler:function(){this.visible=!1,this.user.role="buyer",this.$root.bus.$emit("signup-show",!0),this.$root.bus.$emit("auto-fill-signup-form",this.user)}},created:function(){var e=this;this.$root.bus.$on("visit-method-show",function(t,o){e.visible=t,e.user=o})}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(112),r=o.n(n),s=o(115),i=o.n(s),a=o(116),l=o.n(a),u=o(117),A=o.n(u);t.default={name:"main",components:{"main-carousel":r.a,"signin-dialog":i.a,"signup-dialog":l.a,"visit-method":A.a},data:function(){return{fullscreenLoading:!1,loadingText:"Loading...",carouselData:[{img:"cpb"},{img:"skii"},{img:"givenchy"},{img:"shiseido"}]}},created:function(){var e=this;localStorage.getItem("access-token")&&this.$root.axios.post("/accesstoken").then(function(t){if(0!=t.data.code)return void alert(t.data.status);e.$root.bus.$emit("signed-in",!0,t.data.user)}).catch(function(e){alert("Post /accesstoken error! "+e)}),this.$root.bus.$on("full-screen-loading",function(t,o){e.fullscreenLoading=t,e.loadingText=o})}}},function(e,t,o){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=o(2),r=o(35),s=o.n(r),i=o(33),a=o(34),l=(o.n(a),o(6)),u=(o.n(l),o(9)),A=o.n(u);n.default.config.productionTip=!1,n.default.use(l.Form),n.default.use(l.FormItem),n.default.use(l.Input),n.default.use(l.Button),n.default.use(l.Loading),n.default.prototype.$message=l.Message,A.a.defaults.headers.common.Authorization="bearer "+localStorage.getItem("access-token"),new n.default({el:"#app",router:i.a,template:"<App/>",components:{App:s.a},data:{bus:new n.default,axios:A.a,websocket:new WebSocket("ws://localhost:3000/websocket")}})},,,,,,,,,,,,,,,,,,,function(e,t){},function(e,t){},function(e,t){},function(e,t){},function(e,t){},function(e,t){},function(e,t){},function(e,t){},,function(e,t){e.exports="data:image/png;base64,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"},function(e,t,o){function n(e){o(107)}var r=o(4)(o(76),o(124),n,null,null);e.exports=r.exports},function(e,t,o){function n(e){o(104)}var r=o(4)(o(77),o(121),n,null,null);e.exports=r.exports},function(e,t,o){function n(e){o(105)}var r=o(4)(o(78),o(122),n,"data-v-3bafac42",null);e.exports=r.exports},function(e,t,o){function n(e){o(109)}var r=o(4)(o(79),o(126),n,"data-v-e2b54f36",null);e.exports=r.exports},function(e,t,o){function n(e){o(108)}var r=o(4)(o(80),o(125),n,null,null);e.exports=r.exports},function(e,t,o){function n(e){o(102)}var r=o(4)(o(81),o(119),n,"data-v-16ce5e59",null);e.exports=r.exports},function(e,t,o){function n(e){o(106)}var r=o(4)(o(82),o(123),n,"data-v-7bb0a8ac",null);e.exports=r.exports},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"visitMethod"}},[o("el-dialog",{attrs:{title:"Hello "+e.user.username+"! Now you can:",visible:e.visible},on:{"update:visible":function(t){e.visible=t}}},[o("div",{staticClass:"dialog-body"},[o("el-button",{attrs:{type:"success"},on:{click:e.visitorHandler}},[e._v("Visit as a visitor")]),e._v(" "),o("el-button",{staticStyle:{margin:"0"},attrs:{type:"primary"},on:{click:e.signupHandler}},[e._v("Set password for signup")])],1)])],1)},staticRenderFns:[]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"app"}},[o("shared-header"),e._v(" "),o("router-view")],1)},staticRenderFns:[]}},function(e,t,o){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"header"}},[n("header",[n("img",{attrs:{id:"logo",src:o(111)},on:{click:e.backToHome}}),e._v(" "),n("a",{attrs:{id:"title"}},[e._v("Le Bon Marché")]),e._v(" "),n("a",{directives:[{name:"show",rawName:"v-show",value:!e.signedIn,expression:"!signedIn"}],staticClass:"link signin",on:{click:e.signinClick}},[e._v("Signin")]),e._v(" "),n("a",{directives:[{name:"show",rawName:"v-show",value:!e.signedIn,expression:"!signedIn"}],staticClass:"link signup",on:{click:e.signupClick}},[e._v("Signup")]),e._v(" "),n("el-dropdown",{directives:[{name:"show",rawName:"v-show",value:e.signedIn,expression:"signedIn"}],attrs:{trigger:"hover"},on:{command:e.handleCommand}},[n("span",{staticClass:"el-dropdown-link link"},[e._v(e._s(e.username))]),e._v(" "),n("el-dropdown-menu",{slot:"dropdown"},[n("el-dropdown-item",{attrs:{command:"profile"}},[e._v("Profile")]),e._v(" "),"buyer"==e.role?n("el-dropdown-item",{attrs:{command:"order"}},[e._v("My Order")]):e._e(),e._v(" "),"seller"==e.role?n("el-dropdown-item",{attrs:{command:"product"}},[e._v("My Product")]):e._e(),e._v(" "),n("el-dropdown-item",{attrs:{command:"chatroom"}},[e._v("Chat Room")]),e._v(" "),n("el-dropdown-item",{attrs:{command:"signout",divided:""}},[e._v("Signout")])],1)],1)],1)])},staticRenderFns:[]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"hello"},[o("h1",[e._v(e._s(e.msg))]),e._v(" "),o("h2",[e._v("Essential Links")]),e._v(" "),e._m(0),e._v(" "),o("h2",[e._v("Ecosystem")]),e._v(" "),e._m(1)])},staticRenderFns:[function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("ul",[o("li",[o("a",{attrs:{href:"https://vuejs.org",target:"_blank"}},[e._v("Core Docs")])]),e._v(" "),o("li",[o("a",{attrs:{href:"https://forum.vuejs.org",target:"_blank"}},[e._v("Forum")])]),e._v(" "),o("li",[o("a",{attrs:{href:"https://gitter.im/vuejs/vue",target:"_blank"}},[e._v("Gitter Chat")])]),e._v(" "),o("li",[o("a",{attrs:{href:"https://twitter.com/vuejs",target:"_blank"}},[e._v("Twitter")])]),e._v(" "),o("br"),e._v(" "),o("li",[o("a",{attrs:{href:"http://vuejs-templates.github.io/webpack/",target:"_blank"}},[e._v("Docs for This Template")])])])},function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("ul",[o("li",[o("a",{attrs:{href:"http://router.vuejs.org/",target:"_blank"}},[e._v("vue-router")])]),e._v(" "),o("li",[o("a",{attrs:{href:"http://vuex.vuejs.org/",target:"_blank"}},[e._v("vuex")])]),e._v(" "),o("li",[o("a",{attrs:{href:"http://vue-loader.vuejs.org/",target:"_blank"}},[e._v("vue-loader")])]),e._v(" "),o("li",[o("a",{attrs:{href:"https://github.com/vuejs/awesome-vue",target:"_blank"}},[e._v("awesome-vue")])])])}]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:e.fullscreenLoading,expression:"fullscreenLoading",modifiers:{fullscreen:!0,lock:!0}}],attrs:{id:"main","element-loading-text":e.loadingText}},[o("signin-dialog"),e._v(" "),o("signup-dialog"),e._v(" "),o("visit-method")],1)},staticRenderFns:[]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"carousel"}},[o("el-carousel",{attrs:{interval:4e3,trigger:"click",height:"300px"}},e._l(e.content,function(e){return o("el-carousel-item",{key:e.img},[o("img",{attrs:{src:"static/images/carousel/"+e.img+".png",height:"100%"}})])}))],1)},staticRenderFns:[]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"signup"}},[o("el-dialog",{attrs:{title:"Signup",visible:e.visible,size:"large"},on:{"update:visible":function(t){e.visible=t},close:e.signupDialogCloseHandler}},[o("el-form",{ref:"signupForm",attrs:{model:e.signupForm,rules:e.rules}},[o("el-form-item",{attrs:{label:"Username",prop:"username"}},[o("el-input",{model:{value:e.signupForm.username,callback:function(t){e.signupForm.username=t},expression:"signupForm.username"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Password",prop:"password"}},[o("el-input",{attrs:{type:"password"},model:{value:e.signupForm.password,callback:function(t){e.signupForm.password=t},expression:"signupForm.password"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Repeat Password",prop:"password2"}},[o("el-input",{attrs:{type:"password"},model:{value:e.signupForm.password2,callback:function(t){e.signupForm.password2=t},expression:"signupForm.password2"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Email",prop:"email"}},[o("el-input",{model:{value:e.signupForm.email,callback:function(t){e.signupForm.email=t},expression:"signupForm.email"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Location",prop:"location"}},[o("el-input",{model:{value:e.signupForm.location,callback:function(t){e.signupForm.location=t},expression:"signupForm.location"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Role",prop:"role"}},[o("el-radio",{staticClass:"radio",attrs:{label:"buyer"},model:{value:e.signupForm.role,callback:function(t){e.signupForm.role=t},expression:"signupForm.role"}},[e._v("Buyer")]),e._v(" "),o("el-radio",{staticClass:"radio",attrs:{label:"seller"},model:{value:e.signupForm.role,callback:function(t){e.signupForm.role=t},expression:"signupForm.role"}},[e._v("Seller")])],1),e._v(" "),o("el-form-item",{staticStyle:{display:"none"},attrs:{label:"Signup source",prop:"source"}},[o("el-input",{attrs:{disabled:!0},model:{value:e.signupForm.source,callback:function(t){e.signupForm.source=t},expression:"signupForm.source"}})],1)],1),e._v(" "),o("div",{staticClass:"dialog-footer",slot:"footer"},[o("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("Cancel")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.signupHandler("signupForm")}}},[e._v("Signup")])],1)],1)],1)},staticRenderFns:[]}},function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{attrs:{id:"signin"}},[o("el-dialog",{attrs:{title:"Signin",visible:e.visible},on:{"update:visible":function(t){e.visible=t},close:e.signinDialogCloseHandler}},[o("el-form",{ref:"signinForm",attrs:{model:e.signinForm,rules:e.rules}},[o("el-form-item",{attrs:{label:"Username",prop:"username"}},[o("el-input",{attrs:{"auto-complete":"off"},model:{value:e.signinForm.username,callback:function(t){e.signinForm.username=t},expression:"signinForm.username"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Password",prop:"password"}},[o("el-input",{attrs:{type:"password","auto-complete":"off"},model:{value:e.signinForm.password,callback:function(t){e.signinForm.password=t},expression:"signinForm.password"}})],1),e._v(" "),o("el-form-item",{attrs:{label:"Signin via:"}},e._l(e.thirdPartyLogo,function(t){return o("img",{staticClass:"third-party-logo",attrs:{src:"static/images/third-party/"+t.img+".png"},on:{click:function(o){e.thirdPartyLoginHandler(t.img)}}})}))],1),e._v(" "),o("div",{staticClass:"dialog-footer",slot:"footer"},[o("el-button",{on:{click:function(t){e.visible=!1}}},[e._v("Cancel")]),e._v(" "),o("el-button",{attrs:{type:"primary"},on:{click:function(t){e.signinHandler("signinForm")}}},[e._v("Signin")])],1)],1)],1)},staticRenderFns:[]}}]),[83]);
//# sourceMappingURL=app.bbb1d407bf0ce4618c0b.js.map