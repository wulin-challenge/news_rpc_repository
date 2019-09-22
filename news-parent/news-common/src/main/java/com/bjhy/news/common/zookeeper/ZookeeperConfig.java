package com.bjhy.news.common.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bjhy.news.common.connect.NewsConnect;
import com.bjhy.news.common.util.NewsRpcUtil;
import com.bjhy.news.common.zookeeper.event.ZookeeperCuratorEvent;
import com.bjhy.news.common.zookeeper.event.ZookeeperCuratorEventAdapter;
import com.bjhy.news.common.zookeeper.event.ZookeeperCuratorListener;

import cn.wulin.brace.utils.LoggerUtils;
import cn.wulin.ioc.extension.InterfaceExtensionLoader;

/**
 * zookeeper的核心配置类
 * @author wulin
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
			createNode(NewsRpcUtil.getZkRootNode(), CreateMode.PERSISTENT);//创建根节点
			setNodeListener(NewsRpcUtil.getZkRootNode());
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
	 * 设置节点监听
	 * @param path
	 */
	@SuppressWarnings("resource")
	public void setNodeListener(String path){
		TreeCache treeCache = new TreeCache(curatorFramework, path);
		
		ZookeeperCuratorEvent zookeeperCuratorEvent = new ZookeeperCuratorEventAdapter();
		TreeCacheListener treeCacheListener = new ZookeeperCuratorListener(zookeeperCuratorEvent);
		treeCache.getListenable().addListener(treeCacheListener);
		try {
			treeCache.start();
		} catch (Exception e) {
			LoggerUtils.error(path+" 节点监听启动失败!", e);
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
	 * 得到指定根路径下的指定层级的完整路径
	 * @param rootPath 更路径
	 * @param levelNum 向下递归查找的层级数,0表示当前层级
	 * @return
	 */
	public List<String> getSpecifyLevelPath(String rootPath,int levelNum){
		List<String> paths = new ArrayList<String>();
		if(levelNum<=0) {
			paths.add(rootPath);
		}
		getSpecifyLevelPath(paths, rootPath, levelNum-1);
		return paths;
	}
	
	private void getSpecifyLevelPath(List<String> paths,String rootPath,int levelNum){
		List<String> childrenFullPathList = getChildrenFullPathList(rootPath);
		
		if(levelNum == 0 && childrenFullPathList != null && childrenFullPathList.size() >0) {
			paths.addAll(childrenFullPathList);
		}
		for (String path : childrenFullPathList) {
			getSpecifyLevelPath(paths, path, levelNum-1);
		}
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
		String zkAddress = newsConnect.zookeeperIp()+":"+newsConnect.zookeeperPort();
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
