package com.cl.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BaseChunk {
	public List<TreeNode> list= new ArrayList<>();
	
	public BaseChunk() {
		
	}
	
	//根据括号表达式，存储树结构
	public TreeNode generateTree(List<String> parts){
        Stack<TreeNode> tree = new Stack<TreeNode>();
        for (int i = 0; i < parts.size(); i++) {
			if(!parts.get(i).equals(")") && !parts.get(i).equals(" ")){
				tree.push(new TreeNode(parts.get(i)));
			}else if(parts.get(i).equals(" ")){
				
			}else if(parts.get(i).equals(")")){
				Stack<TreeNode> temp = new Stack<TreeNode>();
				while(!tree.peek().getNodeName().equals("(")){
					if(!tree.peek().getNodeName().equals(" ")){
						temp.push(tree.pop());
					}
				}
				tree.pop();
				TreeNode node = temp.pop();
				while(!temp.isEmpty()){		
					node.addChild(temp.pop());
				}
				tree.push(node);
			}
		}
        TreeNode treeStruct = tree.pop();
        return treeStruct;
	}
	
	//切分括号表达式，并存入列表
	public List<String> stringToList(String treeStr){
		List<String> parts = new ArrayList<String>();
        for (int index = 0; index < treeStr.length(); ++index) {
            if (treeStr.charAt(index) == '(' || treeStr.charAt(index) == ')' || treeStr.charAt(index) == ' ') {
                parts.add(Character.toString(treeStr.charAt(index)));
            } else {
                for (int i = index + 1; i < treeStr.length(); ++i) {
                    if (treeStr.charAt(i) == '(' || treeStr.charAt(i) == ')' || treeStr.charAt(i) == ' ') {
                        parts.add(treeStr.substring(index, i));
                        index = i - 1;
                        break;
                    }
                }
            }
        }
        return parts;
	}
	
	
	//搜寻基本短语块
	public void searchForChunk(TreeNode tn,ArrayList<String> tagList){
		List<TreeNode> children =null;
		boolean flag = true;//是否是基本短语块
		boolean tried = false;//
		if(tn != null)
		{
			children = tn.getChildren();//tn节点的子树			
			if (!children.isEmpty()) {
				for(TreeNode child:children) {
					List<TreeNode> secondChild = child.getChildren();//tn子树的子树
					if(!secondChild.isEmpty()) {					
						for(TreeNode t : secondChild) {																
									if(!t.getChildren().isEmpty()) {								
										flag = false;
									}								
							}
						tried = true;							
						}
					}				
				}
				
				if (tried)
				{
					if (flag) {	
						if(tagList.contains(tn.getNodeName())) {
							tn.setNodeName("[]");
						}
						return;
					}					
				}
				
				for (int i = 0;i<tn.numChildren();i++)//遍历
				{
					searchForChunk(children.get(i),tagList);
				}
			}
		}
	
	//从所有基本短语块中查找目标短语块
	public List<TreeNode> select(String tag){	
		List<TreeNode> target = new ArrayList<>();
		for (int i = 0;i<list.size();i++) {
			if(list.get(i).getNodeName().equals(tag)) {
				target.add(list.get(i));
			}
		}
		return target;
	}		
	}

