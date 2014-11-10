package com.dwk.model.user;

import com.dwk.model.BasicResponse;

public class SigninResponse extends BasicResponse {
  private Integer score ;

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }
}
