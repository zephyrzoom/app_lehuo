/* 变量说明
 * myPaths: 此文件包含参数的地址 (/website/packed/js/libs.js?parametric)
 * myGetPaths: 此文件所在目录 (/website/packed/js/)
 * myGetPacked: 此文件所在目录的上一级目录 (/website/packed/)
 * myGetUrls: 此文件纯地址 (/website/packed/js/libs.js)
 * myGetParam: 此文件地址后的参数 (parametric)
 */
 
var myPaths=document.getElementsByTagName('script'),
	myPaths=myPaths[myPaths.length-1].src.replace(/\\/g,'/'),
	myGetPaths=myPaths.lastIndexOf('/')<0?'.':myPaths.substring(0,myPaths.lastIndexOf('/')),
	myGetPacked=myPaths.lastIndexOf('/')<0?'.':myPaths.substring(0,myPaths.lastIndexOf('js/')),
	myGetUrls=myPaths.split('?')[0],
	myGetParam=myPaths.split('?')[1]||'',
	myArr=myGetParam.split('&'),
	inArr=function(v){
			var i= myArr.length;
			while(i--){
				if(myArr[i]===v){
					return true;
				}
			}
			return false;
		};
	
var jquery='<script type="text/javascript" src="'+myGetPaths+'/jquery/jquery-1.11.0.min.js"></script>\
			<script type="text/javascript" src="'+myGetPaths+'/jquery/jquery-ui-1.10.4.min.js"></script>\
			<script type="text/javascript" src="'+myGetPaths+'/jquery/jquery.mobile.custom.min.js"></script>\
			<script type="text/javascript" src="'+myGetPaths+'/jquery/jquery-migrate-1.1.1.js"></script>',
	easing='<script type="text/javascript" src="'+myGetPaths+'/easing/jquery.easing.1.3.js"></script>',
	slider='<script type="text/javascript" src="'+myGetPaths+'/slider/swiper/idangerous.swiper.min.js"></script>\
			<link type="text/css" href="'+myGetPaths+'/slider/swiper/idangerous.swiper.css" rel="stylesheet"/>',
	touchslider='<script type="text/javascript" src="'+myGetPaths+'/touchslider/jquery.touchslider.min.js"></script>',
	packed='<script type="text/javascript" src="'+myGetPaths+'/packed/global.js"></script>';

if(inArr('jquery')) document.write(jquery);
if(inArr('easing')) document.write(easing);
if(inArr('slider')) document.write(slider);
if(inArr('touchslider')) document.write(touchslider);
if(inArr('packed')) document.write(packed);