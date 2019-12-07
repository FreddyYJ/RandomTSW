package com.github.freddyyj.randomtsw;

public enum SaveFileName {
	ROUTE("route.json"),
	CSX("csx.json"),
	GWE("gwe.json"),
	NEC("nec.json"),
	RT("rt.json"),
	WSR("wsr.json"),
	RSN("rsn.json"),
	LIRR("lirr.json"),
	NTP("ntp.json"),
	MSB("msb.json"),
	TVL("tvl.json"),
	PC("pc.json"),
	RRO("rro.json"),
	ECW("ecw.json");
	private final String text;
	private SaveFileName(String text) {this.text=text;}
	@Override
	public String toString() {
		return text;
	}
}
