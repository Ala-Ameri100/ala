package com.learn.ala.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Options  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<String> val = new ArrayList<String>();
	public List<String> getVal() {
		return val;
	}
	public void setVal(List<String> val) {
		this.val = val;
	}
	
	

}
