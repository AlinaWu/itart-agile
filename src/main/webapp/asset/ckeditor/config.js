/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	config.uiColor = '#FAFAFA';
	
	config.language = 'zh-cn';
	config.image_previewText=' '; //预览区域显示内容
	
	config.filebrowserBrowseUrl= '/mis/attach/upload.do';
	config.filebrowserImageBrowseUrl= '/mis/attach/browse.do?type=Images';
	config.filebrowserUploadUrl= '/mis/attach/upload.do';
	config.filebrowserImageUploadUrl= '/mis/attach/upload.do?type=Images';
	
	config.toolbar = 'Basic';
	config.toolbar_Full =[
    ['Source','-','Save','NewPage','Preview','-','Templates'],
    ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
    ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
    ['BidiLtr', 'BidiRtl'],
    '/',
    ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
    ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    ['Link','Unlink','Anchor'],
    ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
    '/',
    ['Styles','Format','Font','FontSize'],
    ['TextColor','BGColor'],
    ['Maximize', 'ShowBlocks','-','About']
    ];
	config.toolbar_Basic =   
		[   
		 	['Source','-','Preview','-','Templates'],
		 	['Cut','Copy','Paste','PasteText','PasteFromWord','-', 'SpellChecker', 'Scayt'],
		 	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
		 	['BidiLtr', 'BidiRtl'],
		 	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		 	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		 	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		 	['Link','Unlink','Anchor'],
		 	['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		    ['Styles','Format','Font','FontSize'],
		    ['TextColor','BGColor'],
		    ['Maximize', 'ShowBlocks']
		 	
		];  
};
