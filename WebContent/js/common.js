var lyg = lyg || {};lyg.data = lyg.data || {};// 用于存放临时的数据或者对象
/**
 * 获取当前日期，格式"yyyy-MM-dd hh:mm:ss"
 */
lyg.appendZero=function (s){return ("00"+ s).substr((s+"").length);};
lyg.getDate = function (){var d=new Date(); return d.getFullYear() + "-" + lyg.appendZero(d.getMonth() + 1) + "-" + lyg.appendZero(d.getDate());};
lyg.getDateTime = function (){var d=new Date(); return d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate()+' '+d.getHours()+':'+d.getMinutes()+':'+d.getSeconds();};
lyg.getYear=function(){var d=new Date();if(d.getMonth()>6)return d.getFullYear()+1;else return d.getFullYear();}
/**
 * 毫秒数转时间
 *@example lyg.MillisecondToDate(12123233)  返回：3小时22分3秒
 */
lyg.MillisecondToDate=function (msd) {var time = parseFloat(msd) /1000;if (null!= time &&""!= time) {if (time >60&& time <60*60) {time = parseInt(time /60.0) +"分"+ parseInt((parseFloat(time /60.0) -parseInt(time /60.0)) *60) +"秒";}else if (time >=60*60&& time <60*60*24) { time = parseInt(time /3600.0) +"小时"+ parseInt((parseFloat(time /3600.0) -parseInt(time /3600.0)) *60) +"分"+parseInt((parseFloat((parseFloat(time /3600.0) - parseInt(time /3600.0)) *60) - parseInt((parseFloat(time /3600.0) - parseInt(time /3600.0)) *60)) *60) +"秒";}else { time = parseInt(time) +"秒";}}else{ time = "0 时 0 分0 秒";}return time;};
/**
 * 获取url参数值，类似request.getparameter
 * @example $.getUrlParam("url后面参数的名字")
 */
(function($){$.getUrlParam = function(name){var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");var r = window.location.search.substr(1).match(reg);if (r!=null) return unescape(r[2]); return null;}})(jQuery);
/**
 * 格式化字符串formatString功能
 * @example lyg.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 */
lyg.formatString = function(str) {for (var i = 0; i < arguments.length - 1; i++) {str = str.replace("{" + i + "}", arguments[i + 1]);}return str;};
/**
 * JSON对象转换成String
 */
lyg.jsonToString = function(o) {var r = [];if (typeof o == "string")return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";if (typeof o == "object") {if (!o.sort) {for ( var i in o)r.push(i + ":" + lyg.jsonToString(o[i]));if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {r.push("toString:" + o.toString.toString());}r = "{" + r.join() + "}";} else {for (var i = 0; i < o.length; i++)r.push(lyg.jsonToString(o[i]));r = "[" + r.join() + "]";}return r;}return o.toString();};

/**
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 *@example lyg.stringToList("aaa,bbb,ccc44,55,3e") 返回["aaa","bbb","ccc44","55","3e"]
 */
lyg.stringToList = function(value) {if (value != undefined && value != '') {var values = [];var t = value.split(',');for (var i = 0; i < t.length; i++) {values.push('' + t[i]);/* 避免将ID当成数字 */}return values;} else {return [];}};

/**
 * json2-min
 *@example JSON.stringify(value, replacer, space),JSON.parse(text, reviver)
 */
if (typeof JSON !== 'object') {JSON = {};}
(function () {'use strict';function f(n) {return n < 10 ? '0' + n : n;}
    if (typeof Date.prototype.toJSON !== 'function') {Date.prototype.toJSON = function (key) {return isFinite(this.valueOf())? this.getUTCFullYear()+'-'+f(this.getUTCMonth() + 1) + '-' +f(this.getUTCDate())+'T'+f(this.getUTCHours())+':'+f(this.getUTCMinutes())+':'+f(this.getUTCSeconds())+'Z':null;};String.prototype.toJSON=Number.prototype.toJSON=Boolean.prototype.toJSON = function (key){return this.valueOf();};}
    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,gap,indent, meta = {'\b': '\\b','\t': '\\t','\n': '\\n','\f': '\\f','\r': '\\r','"' : '\\"','\\': '\\\\'},rep;
    function quote(string) {escapable.lastIndex = 0; return escapable.test(string) ? '"' + string.replace(escapable, function(a){var c = meta[a];return typeof c === 'string'? c: '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);})+'"':'"'+string+'"';}
    function str(key, holder) { var i,k,v,length,mind = gap,partial,value = holder[key];if (value && typeof value==='object' && typeof value.toJSON==='function') {value = value.toJSON(key);}if (typeof rep === 'function') {value = rep.call(holder, key, value);}switch (typeof value) { case 'string':return quote(value);case 'number': return isFinite(value) ? String(value) : 'null';case 'boolean':case 'null':return String(value);case 'object': if (!value){return 'null';}gap += indent;partial = [];if (Object.prototype.toString.apply(value) === '[object Array]') {length = value.length;for (i = 0; i < length; i += 1) {partial[i] = str(i, value) || 'null';}v = partial.length === 0 ? '[]' : gap ? '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' : '[' + partial.join(',') + ']';gap = mind;return v;}if (rep && typeof rep === 'object') {length=rep.length;for (i=0; i<length;i+=1){if (typeof rep[i] === 'string') {k = rep[i];v = str(k, value);if(v){partial.push(quote(k) + (gap ? ': ' : ':') + v);}}}}else{for(k in value){if (Object.prototype.hasOwnProperty.call(value, k)){v = str(k, value);if(v){partial.push(quote(k) + (gap ? ': ' : ':') + v);}}}}v = partial.length === 0 ? '{}' : gap ? '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' : '{' + partial.join(',') + '}';gap = mind;return v;}}
   if (typeof JSON.stringify !== 'function') {JSON.stringify = function (value, replacer, space) { var i;gap = '';indent = '';if (typeof space === 'number') {for (i = 0; i < space; i += 1) {indent += ' ';}}else if (typeof space === 'string') {indent = space;}rep = replacer;if (replacer && typeof replacer !== 'function' && (typeof replacer !== 'object' || typeof replacer.length !== 'number')) {throw new Error('JSON.stringify');} return str('', {'': value});};}
   if (typeof JSON.parse !== 'function') {JSON.parse = function (text, reviver) { var j;function walk(holder, key) {var k, v, value = holder[key];if (value && typeof value === 'object') {for (k in value) {if (Object.prototype.hasOwnProperty.call(value, k)) {v = walk(value, k);if (v !== undefined) {value[k] = v;}else{delete value[k];}}}}return reviver.call(holder, key, value);}text = String(text);cx.lastIndex = 0; if (cx.test(text)) {text = text.replace(cx, function (a) {return '\\u' +('0000' + a.charCodeAt(0).toString(16)).slice(-4);});}if (/^[\],:{}\s]*$/ .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@').replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']').replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {j = eval('(' + text + ')');return typeof reviver === 'function' ? walk({'': j}, ''): j; } throw new SyntaxError('JSON.parse');};}
}());
/**
 * 去字符串空格
 */
lyg.trim = function(str) {return str.replace(/(^\s*)|(\s*$)/g, '');};
lyg.ltrim = function(str) {return str.replace(/(^\s*)/g, '');};
lyg.rtrim = function(str) {return str.replace(/(\s*$)/g, '');};

/**
 * 将form表单元素的值序列化成对象
 * @example lyg.serializeObject($('#formId'))
 * @requires jQuery
 * @returns object
 */
lyg.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined && this['value'].length > 0) {/*如果表单项的值非空，才进行序列化操作*/
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};
/**
 * 将form表单元素的值序列化成对象//空值也 会序列化
 * @example lyg.serializeObject($('#formId'))
 * @requires jQuery
 * @returns object
 */
lyg.serializeObjectAll = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (this['value'] != undefined) {/*如果表单项的值非空，才进行序列化操作*/
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		}
	});
	return o;
};
/*如果表单项的值空，也进行序列化操作*/
lyg.serializeObjectNull = function(form) {var o = {};$.each(form.serializeArray(), function(index) {if (o[this['name']]) {o[this['name']] = o[this['name']] + "," + this['value'];} else {o[this['name']] = this['value'];}});return o;};

/**
 * 生成随机码，用于ajax后台时IE不使用缓存
 */
lyg.randomUrl=function (){return Math.random()+new Date().getMilliseconds();};



/**
 * Create a cookie with the given key and value and other optional parameters.
 *
 * @example lyg.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example lyg.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example lyg.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example lyg.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain used when the cookie was set.
 *
 * @param String
 *            key The key of the cookie.
 * @param String
 *            value The value of the cookie.
 * @param Object
 *            options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object. If a negative value is specified (e.g. a date in the past), the cookie will be deleted. If set to null or omitted, the cookie will be a session cookie and will not be retained when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will require a secure protocol (like HTTPS).
 * @type undefined
 *
 *
 * Get the value of a cookie with the given key.
 *
 * @example lyg.cookie('the_cookie');
 * @desc Get the value of a cookie.
 *
 * @param String
 *            key The key of the cookie.
 * @return The value of the cookie.
 * @type String
 *
 */
lyg.cookie = function(key, value, options) {
	if (arguments.length > 1 && (value === null || typeof value !== "object")) {
		options = $.extend({}, options);
		if (value === null) {
			options.expires = -1;
		}
		if (typeof options.expires === 'number') {
			var days = options.expires, t = options.expires = new Date();
			t.setDate(t.getDate() + days);
		}
		return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
	}
	options = value || {};
	var result, decode = options.raw ? function(s) {
		return s;
	} : decodeURIComponent;
	return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

//让ie支持 表单input的placeholder属性
lyg.placeholde=function(){var doc=document,inputs=doc.getElementsByTagName('input'),supportPlaceholder='placeholder'in doc.createElement('input'),placeholder=function(input){var text=input.getAttribute('placeholder'),defaultValue=input.defaultValue;if(defaultValue==''){ input.value=text}input.onfocus=function(){ if(input.value===text){this.value=''}};input.onblur=function(){if(input.value===''){this.value=text}}};if(!supportPlaceholder){ for(var i=0,len=inputs.length;i<len;i++){var input=inputs[i],text=input.getAttribute('placeholder');if(input.type==='text'&&text){placeholder(input)}}}}
