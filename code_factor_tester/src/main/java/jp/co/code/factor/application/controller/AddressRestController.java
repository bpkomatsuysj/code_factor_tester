package jp.co.code.factor.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jp.co.code.factor.common.constans.CommonConsts;
import jp.co.code.factor.domain.service.AddressRestService;

@RestController
public class AddressRestController {

  @Autowired
  private AddressRestService addressRestService;

  @GetMapping("/address")
  public String getAddress() {

    int tmp_out = 59800;
    System.out.println(trimTrailingZero(tmp_out));

    int tmpOut = 59800;
    System.out.println(trimTrailingZero(tmpOut));

    return addressRestService.getAddress();
  }

  public static String trimTrailingZero(int number) {
    return String.valueOf(number).replaceAll("0+$", CommonConsts.EMPTY);
  }

  public static String TrimTrailingZero(int number) {
    return String.valueOf(number).replaceAll("0+$", CommonConsts.EMPTY);
  }
}
