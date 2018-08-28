locals {
  aseName = "${data.terraform_remote_state.core_apps_compute.ase_name[0]}"
  ccdApiUrl = "http://ccd-data-store-api-${var.env}.service.${local.aseName}.internal"
  sendLetterUrl = "http://send-letter-producer-${var.env}.service.${local.aseName}.internal"
}


module "div-validation-service" {
  source = "git@github.com:hmcts/moj-module-webapp.git"
  product = "${var.product}-${var.microservice}"
  location = "${var.location}"
  env = "${var.env}"
  ilbIp = "${var.ilbIp}"
  is_frontend = false
  subscription = "${var.subscription}"
  common_tags  = "${var.common_tags}"

  app_settings = {
    //    logging vars
    REFORM_TEAM = "${var.product}"
    REFORM_SERVICE_NAME = "${var.microservice}"
    REFORM_ENVIRONMENT = "${var.env}"
  }
}

