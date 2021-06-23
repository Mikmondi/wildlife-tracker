

$(document).ready(function(){
    $('#endangered-form').hide();
});

$(document).ready(function(){
    $('#common-form').hide();
});


$(document).ready(function(){
    $('#endangeredCheck').on('click', function(){
        if ($('#commonCheck').attr('checked') == true) {
            $('#common-form').hide();
            $('#commonCheck').attr('checked', false);
        }
        $('#endangered-form').toggle(500);
        $('#endangeredCheck').attr('checked', true);
    });
});

$(document).ready(function(){
    $('#commonCheck').on('click', function(){
        if ($('#commonCheck').attr('checked') == true) {
            $('#endangered-form').hide();
            $('#endangeredCheck').attr('checked', false);
        }
        $('#common-form').toggle(500);
        $('#commonCheck').attr('checked', true);
    });
});


