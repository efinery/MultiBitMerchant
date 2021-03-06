package org.multibit.mbm.web.atmosphere;

import org.atmosphere.cpr.AtmosphereResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.multibit.mbm.security.dto.User;
import org.multibit.mbm.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * <p>Reverse Ajax Controller providing the following for clients</p>
 * <ul>
 * <li>Subscription to an alert mechanism tailored to the individual client</li>
 * </ul>
 * <p>Clients will receive an individual alert message of some kind through
 * a broadcast event.</p>
 */
@Controller
@RequestMapping(value = "/v1/alert")
public class AlertMessageController {

  private static final Logger log = LoggerFactory.getLogger(AlertMessageController.class);

  @Resource
  private SecurityService securityService;

  /**
   * Send out a regular alert
   * @param event The Atmosphere event
   */
  @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
  @ResponseBody
  public void alert(final AtmosphereResource<HttpServletRequest, HttpServletResponse> event, Principal principal) {

    final ObjectMapper mapper = new ObjectMapper();

    // Suspend the underlying connection for the client to allow Reverse Ajax
    event.suspend();

    // 
    log.debug("Principal is {}",principal);


    User user = securityService.getUserByPrincipal(principal);

    if (user==null) {
      log.info("Alert subscription without OpenId");
      return;
    } else {
      BroadcastService.INSTANCE.addBroadcaster(user.getId(), event.getBroadcaster());
    }
  }

}

