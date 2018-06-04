package com.cl.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * 树中的节点类
 * 
 *
 */
public class TreeNode {	

	private String nodename;

	private List<TreeNode> children = new ArrayList<TreeNode>();
		
	public TreeNode(){
			
	}
	
	public TreeNode(String nodename){
		this.nodename = nodename;
	}
	/**
	 * 添加孩子节点
	 * @param children 孩子节点的名称
	 */
	public void addChild(String children){
		this.children.add(new TreeNode(children));
	}
	
	/**
	 * 添加孩子节点
	 * @param children 孩子节点
	 */
	public void addChild(TreeNode children){
		this.children.add(children);
	}
	
	/**
	 * 添加若干孩子节点
	 * @param children 孩子节点数组
	 */
	public void addChild(TreeNode[] children){
		for (TreeNode treeNode : children) {
			this.addChild(treeNode);
		}
	}
	
	/**
	 * 添加若干孩子节点
	 * @param children 孩子节点名称组成的数组
	 */
	public void addChild(String[] children){
		for (String treeNode : children) {
			this.addChild(treeNode);
		}
	}
		
	/**
	 * 判断是否是叶子节点
	 * @return
	 */
	public boolean isLeaf(){
		return this.children.size() == 0;
	}
		
	/**
	 * 统计当前父节点下，孩子节点的个数
	 * @return
	 */
	public int numChildren(){
		return this.children.size();
	}
	
	/**
	 * 返回节点名称
	 * @return
	 */
	public String getNodeName(){
		return this.nodename;
	}

	/**
	 * 返回孩子节点列表
	 * @return
	 */
	public List<TreeNode> getChildren(){
		return this.children;
	}

	/**
	 * 重写toString方法，输出有缩进和换行的括号表达式
	 */
	@Override
	public String toString() {
		if(this.children.size() == 0){
			return " "+this.nodename;
		}else{
			String treestr = "";
			treestr = "("+this.nodename+" ";			
			for (TreeNode node:this.children) {
				treestr += node.toString();
			}
			treestr += ")";
			return treestr;
		}
	}
	
	
}
