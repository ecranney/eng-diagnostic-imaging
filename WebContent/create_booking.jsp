<%@page import="dies.models.Technician"%>
<%@page import="dies.services.AppointmentService"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Booking</title>
<meta charset="utf-8">
<meta name="generator" content="jqueryform.com">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="resources/styles/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css">
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.min.js"></script>
<style>
.gaddress .form-group {
	padding-left: 0px;
}

.gaddress .mq-indicator {
	display: none;
}

@media only screen and (min-width: 767px) {
	.gaddress .mq-indicator {
		display: block;
		height: 1px;
		margin-top: -19px;
	}
	.gaddress .form-group.city, .gaddress .form-group.state, .gaddress .form-group.postalCode,
		.gaddress .form-group.country {
		display: inline-block;
		width: 48%;
		vertical-align: top;
	}
}
</style>
</head>
<body>
	<div class="container jf-form">
		<form action='appointment' method='post'>

			<div class="form-group f6 " data-fid="f6">
				<label class="control-label" for="f6">Date</label>
				<div class="input-group date">
					<input type="datetime-local" class="form-control datepicker"
						id="f6" name="appointmentDate" value=""
						data-datepicker-format="DD, MM d, yyyy"
						data-datepicker-startDate="0d" required /> <span
						class="input-group-addon right"><i
						class="glyphicon glyphicon-th"></i> </span>
				</div>
			</div>
			<div class="form-group f7 " data-fid="f7">
				<label class="control-label" for="f7">Time</label> <select
					class="form-control" id="f7" name="appointmentTime">
					<option value="">- Select -</option>
					<option value="Option 1">1.00 pm</option>
					<option value="Option 2">2.00 pm</option>
					<option value="Option 3">3.00 pm</option>
				</select>
			</div>
			<div class="form-group f5 " data-fid="f5">
				<label class="control-label" for="f5">First Name</label>
				<div class="input-group">
					<span class="input-group-addon left"><i
						class="glyphicon glyphicon-user"></i> </span> <input type="text"
						class="form-control" id="f5" name="patientFirstName" value="" />
				</div>
			</div>
			<div class="form-group f4 " data-fid="f4">
				<label class="control-label" for="f4">Last Name</label>

				<div class="input-group">
					<span class="input-group-addon left"><i
						class="glyphicon glyphicon-user"></i> </span> <input type="text"
						class="form-control" id="f4" name="patientLastName" value="" />
				</div>
			</div>
			<div class="form-group f8 " data-fid="f8">
				<label class="control-label" for="f8">Phone</label>

				<div class="input-group">
					<span class="input-group-addon left"><i
						class="glyphicon glyphicon-earphone"></i> </span> <input type="tel"
						class="form-control" id="f8" name="patientMobile" value=""
						placeholder="xxx-xxx-xxxx"
						data-rule-pattern="[0-9]{3,4}[ -.]*[0-9]{3,4}[ -.]*[0-9]{4}"
						data-msg-pattern="Invalid phone number" />
				</div>
			</div>
			<div class="form-group f10 " data-fid="f10">
				<label class="control-label" for="f10">Medicare No</label> <input
					type="text" class="form-control" id="f10" name="patientMedicareNo"
					value="" />
			</div>
			<div class="form-group gaddress f12 " data-fid="f12">
				<label class="control-label">Address</label>

				<div class="form-group addressLine1">
					<label class="control-label sr-only" for="f12_addressLine1">Unit
						No</label> <input type="text" class="form-control gaddress-autocomplete"
						data-gaddress-types="street_number route"
						data-gaddress-name="long_name" id="f12_addressLine1"
						name="patientStreetNo" value=""
						aria-describedby="f12_addressLine1-help-block"
						placeholder="1234 Main St." />
					<p id="f12_addressLine1-help-block" class="description sr-only">
						Street address, P.O. box, company name, c/o</p>
				</div>
				<div class="form-group city">
					<label class="control-label sr-only" for="f12_city">City</label> <input
						type="text" class="form-control" data-gaddress-types="locality"
						data-gaddress-name="long_name" id="f12_city" name="patientCity"
						value="" aria-describedby="f12_city-help-block" placeholder="City" />
					<p id="f12_city-help-block" class="description sr-only">City</p>
				</div>

				<div class="form-group state">
					<label class="control-label sr-only" for="f12_state">State
						/ Province / Region</label> <input type="text" class="form-control"
						data-gaddress-types="administrative_area_level_1"
						data-gaddress-name="long_name" id="f12_state" name="patientState"
						value="" aria-describedby="f12_state-help-block"
						placeholder="State / Province / Region" />
					<p id="f12_state-help-block" class="description sr-only">State
						/ Province / Region</p>
				</div>
				<div class="form-group postalCode">
					<label class="control-label sr-only" for="f12_postalCode">Postal
						/ Zip Code</label> <input type="text" class="form-control"
						data-gaddress-types="postal_code"
						data-gaddress-name="patientUPostalCode" id="f12_postalCode"
						name="patientPostalCode" value=""
						aria-describedby="f12_postalCode-help-block"
						placeholder="Postal / Zip Code" />
					<p id="f12_postalCode-help-block" class="description sr-only">
						Postal / Zip Code</p>
				</div>

			</div>
			<div class="form-group f14 " data-fid="f14">
				<label class="control-label" for="f14">Scan Type</label> <select
					class="form-control" id="f14" name="appointmentType">
					<option value="">- Select -</option>
					<option value="XRAY">XRAY</option>
					<option value="CAT">CAT</option>
					<option value="MRI">MRI</option>
				</select>
			</div>

			<div class="form-group f13 " data-fid="f13">
				<label class="control-label" for="f13">Technician</label> <select
					class="form-control" id="f13" name="technician">

					<option value="">- Select -</option>
					<%
						String valuee = "25/04/2013";
						String time1 = "2017-10-06T17:48:23.558";
						LocalDateTime localDateTime = LocalDateTime.parse(time1);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");

						Date currentDate = new SimpleDateFormat("dd/MM/yyyy").parse(valuee);
						AppointmentService appointmentService = new AppointmentService();
						List<Technician> tList = appointmentService.findAvailableTechnicians(localDateTime);
						for (Technician t : tList) {
					%>
					<option value=<%=t.getId()%>><%=t.getFirstName()%>
					</option>
					<%
						}
					%>
				</select>
			</div>

			<div class="form-group submit f0 " data-fid="f0"
				style="position: relative;">
				<label class="control-label sr-only" for="f0"
					style="display: block;">Submit Button</label>

				<div class="progress"
					style="display: none; z-index: -1; position: absolute;">
					<div class="progress-bar progress-bar-striped active"
						role="progressbar" style="width: 100%"></div>
				</div>
				<button type="submit" class="btn btn-primary btn-lg"
					style="z-index: 1;">Submit</button>
			</div>
			<div class="clearfix"></div>

			<div class="submit">
				<p class="error bg-warning" style="display: none;">Please check
					the required fields.</p>
			</div>
			<div class="clearfix"></div>
		</form>
	</div>

	<script
		src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtXpn6gUreNd7lbpKUKPEgt6oXmVl5BSo&libraries=places"></script>

	<script type="text/javascript">
    ;(function ($, undefined) {


        var alignRight = function () {

            var docW = $(document).width();

            $('.gaddress').each(function () {

                var $fields = $(this).find('.city,.state,.postalCode,.country'),
                    idx = 0, w = 0;
                $fields.each(function (i, e) {
                    w = parseInt($(e).width());
                    if (1 == idx % 2 && docW >= 768) {
                        $(e).css({float: 'right'});
                    } else {
                        $(e).css({float: 'none'});
                    }
                    ;
                    idx++;
                }); // each

            }); // each

        }; // alignRight

        alignRight();
        $(window).resize(alignRight);

        var gaComplete = function ($input) {
            var autocomplete,
                initAutocomplete = function () {
                    $input.focus(geolocate);
                    var options = {

                        types: ['address']
                    };
                    autocomplete = new google.maps.places.Autocomplete(
                        $input.get(0),
                        options
                    );
                    autocomplete.addListener('place_changed', fillInAddress);
                }, // initAutocomplete


                fillInAddress = function () {
                    // Get the place details from the autocomplete object.
                    var place = autocomplete.getPlace();
                    $input.closest('.gaddress').find('[data-gaddress-types]').each(function () {

                        var $t = $(this),
                            types = $t.data('gaddress-types').replace(/\s+/g, ' '),
                            orType = types.indexOf(',') !== -1,
                            types = types.split(orType ? ',' : ' '),
                            name = $t.data('gaddress-name'),
                            values = [];

                        if (!(name == 'long_name' || name == 'short_name')) {
                            name = 'long_name';
                        }
                        ;

                        for (var i = 0; i < place.address_components.length; i++) {
                            var address = place.address_components[i];

                            for (var j = 0; j < types.length; j++) {
                                if (-1 !== $.inArray(types[j], address.types)) {
                                    values.push(address[name]);
                                    if (orType) break; // found one of the defined types
                                }
                                ;
                            }
                            ; // for j

                            if (orType) break; // found one of the defined types
                        }
                        ;// for i

                        $t.val(values.join(' ')).valid();

                    });
                }, // fillInAddress


                geolocate = function () {
                    if (!navigator.geolocation)
                        return;

                    navigator.geolocation.getCurrentPosition(function (position) {
                        var geolocation = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };
                        var circle = new google.maps.Circle({
                            center: geolocation,
                            radius: position.coords.accuracy
                        });
                        autocomplete.setBounds(circle.getBounds());
                    });

                };

            // main
            initAutocomplete();

        };

        init = function () {
            $('input.gaddress-autocomplete').each(function () {
                gaComplete($(this));
            });
        };


        $.fn.gaddress = function (method) {
            if ($.fn.gaddress[method]) {
                return $.fn.gaddress[method].apply(this, Array.prototype.slice.call(arguments, 1));
            }
            else if (typeof method === "object" || !method) {
                return initialize.apply(this, arguments);
            }
            else {
                $.error("Method " + method + " does not exist on jQuery.gaddress");
            }
        };

        $.fn.gaddress.init = init;

    })(jQuery);

    $(document).gaddress('init');
</script>

</body>
</html>
