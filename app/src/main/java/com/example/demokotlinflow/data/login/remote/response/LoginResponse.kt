package com.example.demokotlinflow.data.login.remote.response

data class LoginResponse(
    var update_info: UpdateInfo?,
    var id: Int?,
    var email: String?,
    var tally_account_id: Any?,
    var mobile_number: Any?,
    var alternate_mobile_number: Any?,
    var branch_id: Any?,
    var roles: List<String?>?,
    var manager_id: Int?,
    var fleet_owner_id: Int?,
    var rfid: Any?,
    var name: String?,
    var profile_pic: Any?,
    var aadhar_number: Any?,
    var pan_number: Any?,
    var designation: Any?,
    var charging_station_id: Any?,
    var bank_detail_id: Any?,
    var mobile_number_verified: Boolean?,
    var profile_submitted_time: Any?,
    var status: String?,
    var new_status: Any?,
    var status_updated_at: Any?,
    var user_roles: List<UserRole?>?,
    var reason: Any?,
    var comment: Any?,
    var is_approved: Boolean?,
    var doc_profile_pic: Any?,
    var owner_aadhar: Any?,
    var payment_cheque: Any?,
    var pan_card: Any?,
    var cancelled_cheque: Any?,
    var gst_certificate: Any?,
    var aadhar_card_front: String?,
    var aadhar_card_back: Any?,
    var signature: Signature?,
    var registering_user_signature: RegisteringUserSignature?,
    var saf_pdf: SafPdf?,
    var saf_pdf_customer_copy: SafPdfCustomerCopy?,
    var driving_license: Any?,
    var registration_certificate: Any?,
    var photo_with_rickshaw: Any?,
    var latest_bill: Any?,
    var documents_ids: DocumentsIds?,
    var documents_status: DocumentsStatus?,
    var docs_comments: DocsComments?,
    var security_amount_deposited: Int?,
    var created_at: String?,
    var updated_at: String?,
    var fleet_owner: FleetOwner?,
    var customers_of_fleet_owner: Any?,
    var get_manager: GetManager?,
    var manager_info: ManagerInfo?,
    var charging_stations: Any?,
    var operators: Any?,
    var manager: Manager?,
    var branch: Any?,
    var has_batteries_assigned: Boolean?,
    var cus_nomenclature_id: String?,
    var swaps_count: SwapsCount?,
    var current_offer: CurrentOffer?,
    var offer: Offer?,
    var vehicles: List<Vehicle?>?,
    var category: Any?,
    var employee_id: Any?,
    var operations_view_only: Any?,
    var sales_view_only: Any?,
    var login_mobile_number: String?,
    var service_center: Any?,
    var is_pin_created: Boolean?,
    var set_users_type_id: Any?,
    var nomenclature: Any?,
    var swap_count: Int?,
    var cus_count: CusCount?,
    var last_transactions_amount: Any?,
    var last_swap_time: Any?,
    var circle_cp_flag: Any?,
    var app_view_configuration: AppViewConfiguration?,
    var rejection_reason: Any?,
    var address: Any?,
    var firms: List<Any?>?,
    var bank_details: List<Any?>?,
    var ware_houses: List<Any?>?,
    var charging_zones: List<Any?>?,
    var charging_verifications: List<ChargingVerification?>?,
    var access_token: String?
) {
    class UpdateInfo

    data class UserRole(
        var update_info: UpdateInfo?,
        var id: Int?,
        var user_type: UserType?,
        var status: String?,
        var tally_account_id: Any?,
        var profile_submitted_time: String?,
        var reason: Any?,
        var comment: Any?,
        var status_approved_at: String?,
        var status_rejected_at: Any?,
        var created_at: String?,
        var updated_at: String?,
        var status_comments: List<Any?>?,
        var gl_group_wise_tally_accounts: List<Any?>?
    ) {
        data class UpdateInfo(
            var id: Int?,
            var name: String?,
            var phone_number: String?,
            var nomenclature: String?
        )

        data class UserType(
            var id: Int?,
            var role_name: String?,
            var created_at: String?,
            var updated_at: String?
        )
    }

    data class Signature(
        var url: Any?,
        var usage_allowed: Boolean?
    )

    data class RegisteringUserSignature(
        var url: String?,
        var usage_allowed: Boolean?
    )

    data class SafPdf(
        var url: Any?
    )

    data class SafPdfCustomerCopy(
        var url: Any?
    )

    data class DocumentsIds(
        var profile_pic: Any?,
        var owner_aadhar: Any?,
        var payment_cheque: Any?,
        var pan_card: Any?,
        var gst_certificate: Any?,
        var cancelled_cheque: Any?,
        var aadhar_card_front: Int?,
        var aadhar_card_back: Any?,
        var driving_license: Any?,
        var registration_certificate: Any?,
        var photo_with_rickshaw: Any?,
        var latest_bill: Any?
    )

    data class DocumentsStatus(
        var all_accepted: Boolean?,
        var aadhar_card_front: Any?
    )

    data class DocsComments(
        var aadhar_card_front_comment: Any?,
        var aadhar_card_front_updated_at: String?
    )

    data class FleetOwner(
        var id: Int?,
        var email: String?,
        var password: Any?,
        var created_at: String?,
        var updated_at: String?,
        var mobile_number: String?,
        var roles: List<String?>?,
        var manager_id: Int?,
        var password_digest: String?,
        var rfid: Any?,
        var name: String?,
        var profile_pic: ProfilePic?,
        var alternate_mobile_number: String?,
        var aadhar_number: String?,
        var pan_number: String?,
        var designation: String?,
        var charging_station_id: Any?,
        var bank_detail_id: Any?,
        var mobile_number_verified: Boolean?,
        var is_approved: Boolean?,
        var status: String?,
        var reason: Any?,
        var comment: Any?,
        var security_amount_deposited: Any?,
        var status_updated_at: Any?,
        var profile_pic_id: Any?,
        var profile_submitted_time: Any?,
        var fleet_owner_id: Any?,
        var branch_id: Any?,
        var tally_account_id: Any?,
        var offer_id: Any?,
        var cus_nomenclature_id: Any?,
        var current_offer: Any?,
        var active: Boolean?,
        var sp_id: Any?,
        var operator_charging_zone_id: List<Any?>?,
        var devise_token: String?,
        var employee_id: Any?,
        var profiles: List<Any?>?,
        var vehicle_type_id: Any?,
        var category: Any?,
        var service_center_id: Any?,
        var nomenclature: Any?,
        var sales_view_only: Any?,
        var operations_view_only: Any?,
        var description: Any?,
        var dlr_id: Any?,
        var sub_category_id: Any?,
        var login_mobile_number: Any?,
        var current_status: String?,
        var battery_assign: List<Any?>?,
        var battery_assign_time: Any?,
        var pin: Any?,
        var circle_cp_flag: Boolean?,
        var leave_status: String?,
        var due_amount: Any?,
        var no_of_batteries_returned: Int?,
        var refppid: Any?,
        var glk_lease_rental_amount: Any?,
        var glk_activation_amount: Any?,
        var is_institutional_customer: Boolean?,
        var can_create_lease_offer: Boolean?,
        var blocked_for_swap: Boolean?
    ) {
        data class ProfilePic(
            var url: String?,
            var thumb: Thumb?,
            var show_url: ShowUrl?
        ) {
            data class Thumb(
                var url: String?
            )

            data class ShowUrl(
                var url: String?
            )
        }
    }

    data class GetManager(
        var id: Int?,
        var email: String?,
        var password: Any?,
        var created_at: String?,
        var updated_at: String?,
        var mobile_number: String?,
        var roles: List<String?>?,
        var manager_id: Int?,
        var password_digest: String?,
        var rfid: Any?,
        var name: String?,
        var profile_pic: ProfilePic?,
        var alternate_mobile_number: String?,
        var aadhar_number: String?,
        var pan_number: String?,
        var designation: String?,
        var charging_station_id: Any?,
        var bank_detail_id: Any?,
        var mobile_number_verified: Boolean?,
        var is_approved: Boolean?,
        var status: String?,
        var reason: Any?,
        var comment: Any?,
        var security_amount_deposited: Any?,
        var status_updated_at: Any?,
        var profile_pic_id: Any?,
        var profile_submitted_time: Any?,
        var fleet_owner_id: Any?,
        var branch_id: Any?,
        var tally_account_id: Any?,
        var offer_id: Any?,
        var cus_nomenclature_id: Any?,
        var current_offer: Any?,
        var active: Boolean?,
        var sp_id: Any?,
        var operator_charging_zone_id: List<Any?>?,
        var devise_token: String?,
        var employee_id: Any?,
        var profiles: List<Any?>?,
        var vehicle_type_id: Any?,
        var category: Any?,
        var service_center_id: Any?,
        var nomenclature: Any?,
        var sales_view_only: Any?,
        var operations_view_only: Any?,
        var description: Any?,
        var dlr_id: Any?,
        var sub_category_id: Any?,
        var login_mobile_number: Any?,
        var current_status: String?,
        var battery_assign: List<Any?>?,
        var battery_assign_time: Any?,
        var pin: String?,
        var circle_cp_flag: Boolean?,
        var leave_status: String?,
        var due_amount: Any?,
        var no_of_batteries_returned: Int?,
        var refppid: Any?,
        var glk_lease_rental_amount: Any?,
        var glk_activation_amount: Any?,
        var is_institutional_customer: Boolean?,
        var can_create_lease_offer: Boolean?,
        var blocked_for_swap: Boolean?
    ) {
        data class ProfilePic(
            var url: String?,
            var thumb: Thumb?,
            var show_url: ShowUrl?
        ) {
            data class Thumb(
                var url: String?
            )

            data class ShowUrl(
                var url: String?
            )
        }
    }

    data class ManagerInfo(
        var branch: Any?,
        var address: Address?
    ) {
        data class Address(
            var id: Int?,
            var line_no_1: String?,
            var line_no_2: String?,
            var landmark: String?,
            var pincode: String?,
            var city: String?,
            var state: String?,
            var created_at: String?,
            var updated_at: String?,
            var addressable_id: String?,
            var addressable_type: String?,
            var latitude: Any?,
            var longitude: Any?,
            var operational_address: Boolean?,
            var state_id: Int?
        )
    }

    data class Manager(
        var id: Int?,
        var email: String?,
        var password: Any?,
        var created_at: String?,
        var updated_at: String?,
        var mobile_number: String?,
        var roles: List<String?>?,
        var manager_id: Int?,
        var password_digest: String?,
        var rfid: Any?,
        var name: String?,
        var profile_pic: ProfilePic?,
        var alternate_mobile_number: String?,
        var aadhar_number: String?,
        var pan_number: String?,
        var designation: String?,
        var charging_station_id: Any?,
        var bank_detail_id: Any?,
        var mobile_number_verified: Boolean?,
        var is_approved: Boolean?,
        var status: String?,
        var reason: Any?,
        var comment: Any?,
        var security_amount_deposited: Any?,
        var status_updated_at: Any?,
        var profile_pic_id: Any?,
        var profile_submitted_time: Any?,
        var fleet_owner_id: Any?,
        var branch_id: Any?,
        var tally_account_id: Any?,
        var offer_id: Any?,
        var cus_nomenclature_id: Any?,
        var current_offer: Any?,
        var active: Boolean?,
        var sp_id: Any?,
        var operator_charging_zone_id: List<Any?>?,
        var devise_token: String?,
        var employee_id: Any?,
        var profiles: List<Any?>?,
        var vehicle_type_id: Any?,
        var category: Any?,
        var service_center_id: Any?,
        var nomenclature: Any?,
        var sales_view_only: Any?,
        var operations_view_only: Any?,
        var description: Any?,
        var dlr_id: Any?,
        var sub_category_id: Any?,
        var login_mobile_number: Any?,
        var current_status: String?,
        var battery_assign: List<Any?>?,
        var battery_assign_time: Any?,
        var pin: String?,
        var circle_cp_flag: Boolean?,
        var leave_status: String?,
        var due_amount: Any?,
        var no_of_batteries_returned: Int?,
        var refppid: Any?,
        var glk_lease_rental_amount: Any?,
        var glk_activation_amount: Any?,
        var is_institutional_customer: Boolean?,
        var can_create_lease_offer: Boolean?,
        var blocked_for_swap: Boolean?
    ) {
        data class ProfilePic(
            var url: String?,
            var thumb: Thumb?,
            var show_url: ShowUrl?
        ) {
            data class Thumb(
                var url: String?
            )

            data class ShowUrl(
                var url: String?
            )
        }
    }

    data class SwapsCount(
        var today: Int?,
        var last_7_days: Int?,
        var mtd: Int?,
        var last_month: Int?,
        var current_month: Int?
    )

    data class CurrentOffer(
        var id: Int?,
        var description: Any?,
        var vehicle_type_id: Int?,
        var name: String?,
        var product_id: Int?,
        var offer_name: String?,
        var offer_type_id: Int?,
        var branch_ids: List<Int?>?,
        var customer_validity_limit: Int?,
        var security_amount_display_id: Int?,
        var scheme_id: Int?,
        var inactive: Boolean?,
        var last_activation_date: String?,
        var last_deactivation_date: String?,
        var created_at: String?,
        var updated_at: String?,
        var fleet_owner_id: List<Int?>?,
        var rfid_type_id: List<Int?>?,
        var user_type: String?,
        var category_id: Int?
    )

    data class Offer(
        var id: Int?,
        var description: Any?,
        var vehicle_type_id: Int?,
        var name: String?,
        var product_id: Int?,
        var offer_name: String?,
        var offer_type_id: Int?,
        var branch_ids: List<Int?>?,
        var customer_validity_limit: Int?,
        var security_amount_display_id: Int?,
        var scheme_id: Int?,
        var inactive: Boolean?,
        var last_activation_date: String?,
        var last_deactivation_date: String?,
        var created_at: String?,
        var updated_at: String?,
        var fleet_owner_id: List<Int?>?,
        var rfid_type_id: List<Int?>?,
        var user_type: String?,
        var category_id: Int?
    )

    data class Vehicle(
        var update_info: UpdateInfo?,
        var id: Int?,
        var details: Any?,
        var vehicle_number: Any?,
        var user_id: Int?,
        var registration_number: String?,
        var daily_route_from: String?,
        var daily_route_to: String?,
        var route: Route?,
        var vehicle_type: VehicleType?,
        var vehicle_category: Any?,
        var created_at: String?,
        var updated_at: String?,
        var group: Any?
    ) {
        data class UpdateInfo(
            var id: Int?,
            var name: String?,
            var phone_number: String?,
            var nomenclature: String?
        )

        data class Route(
            var id: Int?,
            var to: String?,
            var from: String?,
            var branch_id: Int?,
            var created_at: String?,
            var updated_at: String?
        )

        data class VehicleType(
            var update_info: UpdateInfo?,
            var id: Int?,
            var type: String?,
            var no_of_old_batteries: Int?,
            var no_of_batteries: Int?,
            var created_at: String?,
            var updated_at: String?,
            var battery_model: BatteryModel?,
            var vehicle_category: VehicleCategory?,
            var battery_unit_prices: List<Any?>?
        ) {
            data class UpdateInfo(
                var id: Int?,
                var name: String?
            )

            data class BatteryModel(
                var update_info: UpdateInfo?,
                var id: String?,
                var name: String?,
                var description: String?,
                var istelematicsavailble: Boolean?,
                var weight: Int?,
                var length: Double?,
                var width: Double?,
                var height: Int?,
                var capacity_ah: Int?,
                var capacity_volt: Int?,
                var committed_swap_cycles: Int?,
                var battery_type_id: Int?,
                var created_at: String?,
                var updated_at: String?,
                var ean_code: String?
            ) {
                class UpdateInfo
            }

            data class VehicleCategory(
                var update_info: UpdateInfo?,
                var id: Int?,
                var name: String?,
                var created_at: String?,
                var updated_at: String?
            ) {
                class UpdateInfo
            }
        }
    }

    data class CusCount(
        var all: Int?,
        var approved: Int?,
        var draft: Int?,
        var submitted: Int?,
        var rejected: Int?,
        var pending: Int?
    )

    data class AppViewConfiguration(
        var customers: Boolean?,
        var operators: Boolean?,
        var inventory: Boolean?,
        var earnings: Boolean?
    )

    data class ChargingVerification(
        var update_info: Any?,
        var id: Int?,
        var mobile_number: String?,
        var user_id: Int?,
        var fo_driver_name: Any?,
        var fo_current_driver: Any?,
        var rfid: Rfid?
    ) {
        data class Rfid(
            var id: Int?,
            var rfid: Any?,
            var chip_type: String?,
            var assigned_at: String?,
            var user_id: Int?,
            var created_at: String?,
            var updated_at: String?,
            var status: String?,
            var invoice_number: String?,
            var invoice_time: Any?,
            var last_blocked_date: Any?,
            var serial_no: String?,
            var rfid_model_id: Int?,
            var inventory_code: String?,
            var tally_purchase_ref_no: Any?,
            var cs_ware_house_id: Any?,
            var tally_transaction_no: Any?,
            var rfid_type_id: Int?,
            var rfid_request: Boolean?,
            var supplier_id: Any?,
            var blocked_for_transfer: Boolean?,
            var users_type_id: Int?,
            var service_center_id: Any?,
            var owner_type: String?,
            var owner_id: String?,
            var owner_users_type_id: Int?,
            var base_cs_ware_house_id: Any?,
            var scheme_id: Int?,
            var fg_number: String?,
            var is_capitalized: Any?,
            var uploader_history_id: Int?
        )
    }
}