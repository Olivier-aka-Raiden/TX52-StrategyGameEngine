package tx52.environment;

import java.util.Stack;

/**
 * a classic iterator usable with the method next() and hasnext() like any iterator
 * @author Dudul
 *
 */
public class DepthFirstIterator {
	
	Stack<TreeNode> stack = new Stack<TreeNode>();
	
	DepthFirstIterator(RTree t) {
		if(t.getRoot()!=null){
			stack.push(t.getRoot());
		}
	}
	
	public DepthFirstIterator(TreeNode node){
		if(node!=null){
			stack.push(node);
		}
	}
	
	public boolean hasNext(){
		return !stack.isEmpty();
	}
	
	public TreeNode next(){
		TreeNode top = stack.pop();
		if(top.hasChild()){
			for(TreeNode child : top.getChildren()){
				stack.push(child);
			}
		}
		return top;
	}	

}
