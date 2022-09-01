package com.ctv.geoservice.web;

import com.ctv.geoservice.geolocalize.GeolocalizationService;
import com.ctv.geoservice.geolocalize.GeoserviceProjectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageConsoleController extends GeoServiceAbstractController {

    private static final Logger log = LoggerFactory.getLogger(ManageConsoleController.class);

    @Autowired
    GeolocalizationService geolocalizationService;

    @Autowired
    private GeoserviceProjectConfig geoserviceConfiguration;

    @Value("${application.version}")
    private String appVersion;

    @RequestMapping("")
    public String root(Model model) {
        return renderManageTemplate(model);
    }

    /**
     * Puts in context all of the necessary objects to render the manager console page.
     *
     * @param model
     * @return
     */
    private String renderManageTemplate(Model model) {
        log.info("Logger test message");
        model.addAttribute("version", appVersion);
        model.addAttribute("deliveries", geoserviceConfiguration.getDeliveries());
        return "manage";
    }
}
