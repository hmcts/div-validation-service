locals {
  aseName = "${data.terraform_remote_state.core_apps_compute.ase_name[0]}"
  ccdApiUrl = "http://ccd-data-store-api-${var.env}.service.${local.aseName}.internal"
  sendLetterUrl = "http://send-letter-producer-${var.env}.service.${local.aseName}.internal"
  
  asp_name = "${var.env == "prod" ? "div-vs-prod" : "${var.raw_product}-1-${var.env}"}"
  asp_rg = "${var.env == "prod" ? "div-vs-prod" : "${var.raw_product}-1-${var.env}"}"
}


module "div-validation-service" {
  source                          = "git@github.com:hmcts/moj-module-webapp.git"
  product                         = "${var.product}-${var.microservice}"
  location                        = "${var.location}"
  env                             = "${var.env}"
  ilbIp                           = "${var.ilbIp}"
  is_frontend                     = false
  subscription                    = "${var.subscription}"
  common_tags                     = "${var.common_tags}"
  asp_name                        = "${local.asp_name}"
  asp_rg                          = "${local.asp_rg}"

  app_settings = {
    //    logging vars
    REFORM_TEAM = "${var.product}"
    REFORM_SERVICE_NAME = "${var.microservice}"
    REFORM_ENVIRONMENT = "${var.env}"
  }
}

