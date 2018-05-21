package com.bjhy.news.common.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.util.NewsConstants;

import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * zookeeper的核心配置类
 * @author wubo
 *
 */
public class ZookeeperConfig {
	private Logger logger = LoggerFactory.getLogger(ZookeeperConfig.class);
	private static ZookeeperConfig zookeeperConfig;
	private CuratorFramework curatorFramework;
	
	/**
	 * 得到连接配置信息
	 */
	private NewsConnect newsConnect = InterfaceExtensionLoader.getExtensionLoader(NewsConnect.class).getAdaptiveExtension();
	
	private ZookeeperConfig(){}
	
	/**
	 * 连接zookeeper
	 */
	public void connectZk(){
		try {
			connectZookeeperCurator();
			createNode(NewsConstants.ZK_ROOT_NODE, CreateMode.PERSISTENT);//创建根节点
		} catch (IOException e){
			logger.error("zookeeper连接失败!请检测zookeeper地址",e);
		}
	}
	
	/**
	 * 判断节点是否存在,存在就返回true,否则返回false
	 * @param path
	 * @return
	 */
	public Boolean existsNode(String path){
		try {
			if(curatorFramework.checkExists().forPath(path) == null){
				return false;
			}
		} catch (Exception e) {
			logger.error(path+" 节点创建失败!", e);
		}
		return true;
	}
	
	/**
	 * 创建节点:这里的节点数据为空
	 * @param node 节点
	 * @param createMode 节点模式
	 */
	public String createNode(String path,CreateMode createMode){
		try {
			if(curatorFramework.checkExists().forPath(path) == null){
				return curatorFramework.create().creatingParentsIfNeeded().withMode(createMode).forPath(path);
			}
		} catch (Exception e) {
			logger.error(path+" 节点创建失败!", e);
		}
		return null;
	}
	
	/**
	 * 设置节点数据
	 * @param path
	 * @param data
	 */
	public void setNodeData(String path,byte[] data){
		try {
			if(curatorFramework.checkExists().forPath(path) != null){
				curatorFramework.setData().forPath(path, data);
			}
		} catch (Exception e) {
			logger.error(path+" 设置节点数据失败!", e);
		}
	}
	
	/**
	 * 获取当前节点下面的孩子节点
	 * @param path 
	 * @return
	 */
	public List<String> getChildrens(String path){
		try {
			if(curatorFramework.checkExists().forPath(path) != null){
				return curatorFramework.getChildren().forPath(path);
			}
		} catch (Exception e) {
			logger.error(path+" 获取孩子节点失败!", e);
		}
		return null;
	}
	
	/**
	 * 得到某个节点下孩子的全路径
	 * @param path
	 * @return
	 */
	public List<String> getChildrenFullPathList(String path){
		try {
			if(curatorFramework.checkExists().forPath(path) != null){
				List<String> fullPathList = new ArrayList<>();
				List<String> forPath = curatorFramework.getChildren().forPath(path);
				for (String childrenPath : forPath) {
					fullPathList.add(path+"/"+childrenPath);
				}
				return fullPathList;
			}
		} catch (Exception e) {
			logger.error(path+" 获取孩子节点失败!", e);
		}
		return null;
	}
	
	/**
	 * 获取节点数据
	 * @param path
	 * @return
	 */
	public byte[] getNodeData(String path){
		try {
			if(curatorFramework.checkExists().forPath(path) != null){
				byte[] nodeData = curatorFramework.getData().forPath(path);
				return nodeData;
			}
		} catch (Exception e) {
			logger.error(path+" 获取节点数据失败!", e);
		}
		return null;
	}
	
	/**
	 * 删除节点
	 * @param path
	 */
	public void removeNode(String path){
		try {
			if(curatorFramework.checkExists().forPath(path) != null){
				curatorFramework.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
			}
		} catch (Exception e) {
			logger.error(path+" 删除节点失败!", e);
		}
	}
	
	
	/**
	 * 连接zookeeper
	 * @throws IOException
	 */
	private void connectZookeeperCurator() throws IOException{
		String zkAddress = newsConnect.zookeeperId()+":"+newsConnect.zookeeperPort();
		curatorFramework = CuratorFrameworkFactory.newClient(zkAddress, new ExponentialBackoffRetry(30000, 3));
		curatorFramework.start();
	}

	public static ZookeeperConfig getInstance(){
		if(zookeeperConfig == null){
			zookeeperConfig = new ZookeeperConfig();
		}
		return zookeeperConfig;
	}
	
}