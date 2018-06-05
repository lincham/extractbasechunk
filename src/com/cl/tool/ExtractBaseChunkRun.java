package com.cl.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ExtractBaseChunkRun {		
	
	public static void main(String[] args) throws IOException
	{
		String in = null;//输入文件路径
		String out = null;//输出文件路径
		String chunkTag = null;//要提取的标记
		for(int i = 0;i<args.length;i++) {
			if(args[i].equals("-in")) {
				in = args[i+1];
				i++;
			}
			if(args[i].equals("-out")) {
				out = args[i+1];
				i++;
			}	
			if(args[i].equals("-chunkTag")) {
				chunkTag = args[i+1];
				i++;
			}
		}
		
		List<TreeNode> target = run(in,chunkTag);
		writeFile(target,out);
	}	
	
	public static List<TreeNode> run(String fileIn,String chunkTag) throws IOException{
		StringBuilder sb = new StringBuilder();//存放读进来的数据		
		BaseChunk bc = new BaseChunk();
		ArrayList<String> tagList = new ArrayList<>();
		List<TreeNode> treeList = new ArrayList<>(); //存放标记了基本语块的树结构
		
		//将要提取的基本组块标记存放进列表
		if (chunkTag.contains(",")) {
			String[] tag = chunkTag.split(",");	
			for(String s : tag) {
				tagList.add(s);
			}
		}else {			
			tagList.add(chunkTag);
		}
		
		FileInputStream fis = new FileInputStream(fileIn);
		InputStreamReader isr = new InputStreamReader(fis,"GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = br.readLine();
		while ((str=br.readLine())!=null)
		{
			if (str.contains("("))
			{
				sb.append(str.trim()+" ");
			}
			else 
			{
				if(!(sb.length()==0))
				{			
					List<String> a = bc.stringToList(sb.toString());
					TreeNode t = bc.generateTree(a);
					bc.searchForChunk(t,tagList);
					treeList.add(t);
					sb.delete(0,sb.length());
				}
			}			
		}
		br.close();
		return treeList;
	}
	
	public static void writeFile(List<TreeNode> target,String fileOut) throws IOException {
		FileWriter fw = new FileWriter(fileOut);
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0;i<target.size();i++) {
			bw.write(target.get(i).toString());
			bw.newLine();
		}
		bw.close();
	}

}
