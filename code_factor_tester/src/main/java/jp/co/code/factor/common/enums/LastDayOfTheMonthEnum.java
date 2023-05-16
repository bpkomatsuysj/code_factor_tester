package jp.co.code.factor.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 月末日であるかを管理するEnumクラス.
 */
@AllArgsConstructor
@Getter
public enum LastDayOfTheMonthEnum {

  /** 0:出力する. */
  YES(0, "月末日である"),
  /** 1:出力しない. */
  NO(1, "月末日ではない");

  private int code;
  private String label;

  /**
   * インスタンス生成.
   * 
   * @param code コード
   * @return インスタンス
   */
  public static LastDayOfTheMonthEnum getInstance(int code) {
    for (LastDayOfTheMonthEnum o : LastDayOfTheMonthEnum.values()) {
      if (o.getCode() == code) {
        return o;
      }
    }
    return null;
  }
}
