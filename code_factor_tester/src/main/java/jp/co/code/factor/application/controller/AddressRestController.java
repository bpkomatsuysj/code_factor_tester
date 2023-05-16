package jp.co.code.factor.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.code.factor.domain.service.AddressRestService;

@RestController
public class AddressRestController {

  @Autowired
  private AddressRestService addressRestService;

  @GetMapping("/address")
  public String getAddress() {
    return addressRestService.getAddress();
  }
}
