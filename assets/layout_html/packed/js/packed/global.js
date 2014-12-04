$(document).ready(function(){
	
	document.addEventListener("touchstart", function() {},false);
	
	if($('.swiper-container').length > 0){
		var mySwiper = new Swiper('.swiper-container',{
			mode:'horizontal',
			loop: true,
			autoplay: 5000,
			speed: 800
		}); 
	}
	
});

    function footToLogin() {
        window.top.location = "Login.html";
    }
	function footToRegister(){
		window.top.location="Register.html";
	}

function init_star(count,cur,ctn,txt){
	var s0 = 'star',
		s1 = 'star-focus';
	show_star(txt,cur);
	for(var i=1;i<=count;i++){
		var p = (i<=cur) ? s1 : s0;
		$('<span/>')
		.attr({'class':p,'flag':i})
		.css('cursor','pointer')
		.mouseover(function(){
				var cur = parseInt($(this).attr('flag'));
				$('#'+ctn).children().each(function(){
					var i = parseInt($(this).attr('flag'));
					var p = (i<=cur) ? s1 : s0;
				});
				show_star(txt,cur);
			})
		.click(function(){
				$('#'+ctn).attr('star',$(this).attr('flag'));
			})
		.appendTo($('#'+ctn));
	}
	$('#'+ctn).attr('star',cur).click(function(){
		var cur = parseInt($(this).attr('star'));
		$(this).children().each(function(){
			var p = ($(this).attr('flag') <= cur) ? s1 : s0;
			$(this).attr('class',p);
		});
		show_star(txt,cur);
	});
}

function show_star(txt,cur){
	$('#'+txt).val(cur);
	$('#'+txt).change();
}


function clean_val(){
	var clean = $('.search-clean');
	clean.append('<span class="clean-val"></span>');
	clean_val_change(clean);
	clean.find('input[type=search]').change(function(){
		clean_val_change(clean);
	});
}

function clean_val_change(s){
	var txt = s.find('input[type=search]');
	if(txt.val() != ''){
		$('.clean-val').show().click(function(){
			txt.val('');
			$(this).hide();
		});
	}else{
		$('.clean-val').hide();
	}
}


function list_accordion(){
	$('.list-accordion li').each(function(){
        $(this).find('.title').click(function(){
			if($(this).parent().hasClass('current')){
				$(this).parent().removeClass('current');
				$(this).next('.content').stop().slideUp();
			}else{
				$(this).parent().addClass('current');
				$(this).next('.content').hide().stop().slideDown();
			}
		});
    });
}


function amount(e,m){
	if(m == '+'){
		$('#'+e).val( parseInt($('#'+e).val()) + 1 );
	}else if(m == '-'){
		if(parseInt($('#'+e).val()) > 0){
			$('#'+e).val( parseInt($('#'+e).val()) - 1 );
		}else{
			$('#'+e).val( 0 );
		}
	}
}


function detail_select(){
	var _parent = $('.detail-select-box'),
		_switch = _parent.find('.detail-select-switch'),
		_title = _parent.find('.detail-select-title'),
		_content = _parent.find('.detail-select');
	_content.find('.close').css({
		'top': 0
	});
	_switch.click(function(){
		if(_parent.hasClass('detail-select-box-show')){
			_parent.removeClass('detail-select-box-show');
			_content.find('.close').stop().animate({
				top: 0
			},function(){
				_title.fadeIn();
				_content.stop().slideUp();
			});
		}else{
			_title.fadeOut();
			_content.hide().stop().slideDown(function(){
				_content.find('.close').stop().animate({
					top: -13
				});
			});
			_parent.addClass('detail-select-box-show');
		}
	});
}


function swipe_del(){
	$('.fav-list-edit li').each(function(){
		$(this).on('swipeleft',function(){
			swipe_del_close($('.del_open'));
			swipe_del_open($(this));
		}).on('swiperight',function(){
			swipe_del_close($(this));
		}).on('click',function(){
			if($(this).hasClass('del_open')){
				swipe_del_close($(this));
			}else{
				swipe_del_close($('.del_open'));
				swipe_del_open($(this));
			}
		});
    });
}

function swipe_del_open(e){
	e.stop().animate({
		left: -100
	}).addClass('del_open');
}

function swipe_del_close(e){
	e.stop().animate({
		left: 0
	}).removeClass('del_open');
}


function t_hd() {
    var popup = $('.popup'),
		resize = function () {
		    popup.height($(window).height())
		};
    resize();
    $(window).bind('resize', resize);
    $('#t_taba, #t_tabb').tabs();
    var show = false;
    $('.t_hd .items .item').each(function (index, element) {
        var the = $(this);
        the.click(function () {
            if (!show) {
                show = true;
                $('html,body').css({
                    'height': '100%',
                    'overflow': 'hidden'
                });
                $('#popup_' + (index + 1)).show().find('.popup_box').stop().animate({
                    'margin-top': 0
                }, { duration: 700, easing: 'easeInOutExpo' });
                the.addClass('active');
            } else {
                show = false;
                $('html,body').css({
                    'height': 'auto',
                    'overflow': 'auto'
                });
                $('.popup').find('.popup_box').stop().animate({
                    'margin-top': -300
                }, { duration: 700, easing: 'easeInOutExpo', complete: function () { $(this).parent().stop().fadeOut(); } });
                the.removeClass('active');
                the.siblings().removeClass('active');
            }
        });
    });
    $('.t_hd .popup .bottom').each(function (index, element) {
        var the = $(this);
        the.click(function () {
            if (show) {
                show = false;
                $('html,body').css({
                    'height': 'auto',
                    'overflow': 'auto'
                });
                $('.popup').find('.popup_box').stop().animate({
                    'margin-top': -300
                }, { duration: 700, easing: 'easeInOutExpo', complete: function () { $(this).parent().stop().fadeOut(); } });
                $('.t_hd .items .item').removeClass('active');
            }
        });
    });
}

function sliderID(eID,e){
	var eID = new Swiper(e,{
		mode:'horizontal',
		loop: true,
		onInit: function(){
			num();
		},
		onSlideChangeEnd: function(){
			num();
		}
	}); 
	var num = function(){
		$(e).find('.num').html(eID.activeLoopIndex + 1 + '/' + (eID.slides.length - 2));
	}
}

function bhsxs(){
	$('html, body').css({
		'height'	: '100%',
		'overflow'	: 'hidden'
	});
	$('.sx_li').stop().fadeIn().find('ul').stop().animate({
		right		: 0
	},{duration:700, easing:'easeInOutExpo'});
	bhsxl();
}
function bhsxh(){
	$('html, body').css({
		'height'	: 'auto',
		'overflow'	: 'auto'
	});
	$('.sx_li').find('ul').stop().animate({
		right		: '-50%'
	},{duration:700, easing:'easeInOutExpo', complete:function(){$(this).parent().stop().fadeOut();}});
}
function bhsxl(){
	$('.sx_li ul').each(function(index, element){
        var  the = $(this);
		the.find('li .t').click(function(){
			if($(this).hasClass('active')){
				$(this).removeClass('active').next('.items').stop().slideUp();
			}else{
				$(this).addClass('active').next('.items').stop().slideDown();
			}
		});
    });
	$('.sx_li .close').click(function(){
		bhsxh();
	});
}


function tuan_del(){
	$('.tuan_wrap .row').each(function(){
		$(this).on('swipeleft',function(){
			tuan_del_close($('.del_open'));
			tuan_del_open($(this));
		}).on('swiperight',function(){
			tuan_del_close($(this));
		});
/*		.on('click',function(){
			if($(this).hasClass('del_open')){
				tuan_del_close($(this));
			}else{
				tuan_del_close($('.del_open'));
				tuan_del_open($(this));
			}
		})*/
    });
}

function tuan_del_open(e){
	e.stop().animate({
		left: -40
	}).addClass('del_open');
}

function tuan_del_close(e){
	e.stop().animate({
		left: 0
	}).removeClass('del_open');
}


function tuan_select(){
	var tuan_top = $('.tuan_wrap .top'),
		select_box = tuan_top.find('.select_box');
	tuan_top.find('.select .click').click(function(){
		if(select_box.is(':visible')){
			select_box.hide();
		}else{
			select_box.show();
		}
	});
	$(window).click(function(event){
		if($(event.target).parents('.select_box').length == 0 && $(event.target).attr('class') != 'click'){
			select_box.hide();
		}
	});
}