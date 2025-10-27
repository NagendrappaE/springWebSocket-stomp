package com.ece.websocket.stomp.bean;

import lombok.Data;

@Data
public class Trade {
	  private String userName;
      private String symbol;
      private int quantity;
      private String type;
}
