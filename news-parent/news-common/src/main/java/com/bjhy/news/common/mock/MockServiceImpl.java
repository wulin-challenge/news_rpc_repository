package com.bjhy.news.common.mock;

public class MockServiceImpl implements MockService{

	@Override
	public Integer echo(Integer i) {
		return (i+1);
	}

}
