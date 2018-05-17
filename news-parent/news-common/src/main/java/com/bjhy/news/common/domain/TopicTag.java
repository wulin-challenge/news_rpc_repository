package com.bjhy.news.common.domain;
/**
	 * 客户端标识和 客户端应用标识 (主题和标记/标签)
	 * @author wubo
	 */
	public class TopicTag{
		/**
		 * 客户端标识(主题)
		 */
		private String topic;
		
		/**
		 * 客户端应用标识(标记/标签)
		 */
		private String tag;
		
		public TopicTag(){}
		
		public TopicTag(String topic, String tag) {
			super();
			this.topic = topic;
			this.tag = tag;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
	}