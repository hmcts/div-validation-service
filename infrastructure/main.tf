locals {
  aseName = "core-compute-${var.env}"
  asp_name = "${var.env == "prod" ? "div-vs-prod" : "${var.raw_product}-${var.env}"}"
  asp_rg = "${var.env == "prod" ? "div-vs-prod" : "${var.raw_product}-${var.env}"}"
}


module "div-validation-service" {
  source                          = "git@github.com:hmcts/moj-module-webapp.git"
  product                         = "${var.product}-${var.microservice}"
  location                        = "${var.location}"
  env                             = "${var.env}"
  ilbIp                           = "${var.ilbIp}"
  is_frontend                     = false
  subscription                    = "${var.subscription}"
  appinsights_instrumentation_key = "${var.appinsights_instrumentation_key}"
  common_tags                     = "${var.common_tags}"
  asp_name                        = "${local.asp_name}"
  asp_rg                          = "${local.asp_rg}"
  instance_size                   = "${var.instance_size}"

  app_settings = {
    //    logging vars
    REFORM_TEAM = "${var.product}"
    REFORM_SERVICE_NAME = "${var.microservice}"
    REFORM_ENVIRONMENT = "${var.env}"
  }
}

