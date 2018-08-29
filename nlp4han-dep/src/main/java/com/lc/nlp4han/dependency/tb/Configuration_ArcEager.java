package com.lc.nlp4han.dependency.tb;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class Configuration_ArcEager extends Configuration
{

	public Configuration_ArcEager(ArrayDeque<Vertice> stack, LinkedList<Vertice> wordsBuffer, ArrayList<Arc> arcs)
	{

		super(stack,wordsBuffer,arcs);
	}

	public Configuration_ArcEager(String[] words, String[] pos)
	{
		super(words,pos);
	}

	public Configuration_ArcEager()
	{
	}

	// wordpos包括“核心”
//	public  Configuration_ArcEager generateConfByActions(String[] wordpos, String[] priorActions)
//	{
//		String[] words = new String[wordpos.length / 2 + 1];
//		String[] poses = new String[wordpos.length / 2 + 1];
//		for (int i = 0; i < words.length; i++)
//		{
//			String[] word_pos = wordpos[i].split("/");
//			words[i] = word_pos[0];
//			poses[i] = word_pos[1];
//		}
//		Configuration_ArcEager conf = new Configuration_ArcEager(words, poses);
//		for (String preAction : priorActions)
//		{
//			ActionType at = ActionType.toType(preAction);
//			conf.transition(at);
//		}
//		return conf;
//	}

	/**
	 * 当栈顶元素和buffer第一个单词没有关系时，判断是否reduce
	 * 
	 * @return 有关系返回true
	 */
	public boolean canReduce(String[] dependencyIndices)
	{// words包括人工添加的“核心”
		// if (wordsBuffer.isEmpty())
		// return false;
		Vertice[] wordsInStack = stack.toArray(new Vertice[stack.size()]);
		int indexOfWord_Si;// 该单词在words中索引
		int indexOfWord_B1 = wordsBuffer.get(0).getIndexOfWord();
		int headIndexOfWord_Si;// 栈顶单词中心词在words中的索引
		int headIndexOfWord_B1 = Integer.parseInt(dependencyIndices[indexOfWord_B1 - 1]);
		for (int i = 1; i < stack.size(); i++)
		{
			indexOfWord_Si = wordsInStack[i].getIndexOfWord();// 该单词在words中索引
			if (indexOfWord_Si == 0)
				headIndexOfWord_Si = -1;
			else
				headIndexOfWord_Si = Integer.parseInt(dependencyIndices[indexOfWord_Si - 1]);// 栈顶第i个单词中心词在words中的索引
			if (indexOfWord_Si == headIndexOfWord_B1 || indexOfWord_B1 == headIndexOfWord_Si)
				return true;
		}
		return false;
	}

	// 共四类基本操作RIGHTARC_SHIFT、LEFTARC_REDUCE、SHIFT、REDUCE
	public void transition(ActionType actType)
	{
		switch (actType.getBaseAction())
		{
		case "RIGHTARC_SHIFT":
			addArc(new Arc(actType.getRelation(), stack.peek(), wordsBuffer.get(0)));
			shift();
			break;
		case "LEFTARC_REDUCE":
			addArc(new Arc(actType.getRelation(), wordsBuffer.get(0), stack.peek()));
			reduce();
			break;
		case "SHIFT":
			shift();
			break;
		case "REDUCE":
			reduce();
			break;
		default:
			throw new IllegalArgumentException("参数不合法!");
		}
	}

	public void reduce()
	{
		if (!stack.isEmpty())
		{
			stack.pop();
		}
		else
		{
		}
	}

	public static void main(String[] args)
	{
		String[] words = { "根", "我", "爱", "自然", "语言", "处理" };
		String[] pos = { "0", "1", "2", "3", "4", "5" };
		LinkedList<Vertice> buffer = Vertice.getWordsBuffer(words, pos);
		ArrayDeque<Vertice> stack = new ArrayDeque<Vertice>();
		Configuration_ArcEager conf = new Configuration_ArcEager(stack, buffer, new ArrayList<Arc>());
		System.out.println(conf.toString());
		conf.shift();
		System.out.println(conf.toString());
		conf.reduce();
		System.out.println(conf.toString());
	}
}
