package tx52.environment;

import java.util.ArrayList;
import java.util.Stack;

import org.arakhne.afc.math.continous.object2d.Rectangle2f;

public class TreeNode {

	private Rectangle2f box;
	private ArrayList<EnvironmentObject> objects;
	private TreeNode[] children = null;
	private TreeNode parent = null;
	

	TreeNode(Rectangle2f box) {  //Création d'une branche de l'arbre en lui indiquant sa portée (via un rectangle2f)
		this.setBox(box);
		objects = new ArrayList<EnvironmentObject>();
	}

	/**
	 * add an object in the tree
	 * @param o
	 */
	public void add(EnvironmentObject o) {
		if(objects.size()<15 && getChildren()== null){  //15 is an arbitrary max number of object
			objects.add(o);
			o.node=this;
		}else{
			if(getChildren()==null){
				setChildren(new TreeNode[4]); //4 is an arbitrary number of child
				for(int i=0;i<4;i++){
					getChildren()[i]=new TreeNode(createChildBox(i));
					getChildren()[i].parent = this;
				}
			}
			
			ArrayList<EnvironmentObject> P = objects;
			objects = new ArrayList<EnvironmentObject>();
			 
			for(EnvironmentObject e : P){
				addInChild(e);
			}
			addInChild(o);
		}
		
		
	}
	
	public ArrayList<EnvironmentObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<EnvironmentObject> objects) {
		this.objects = objects;
	}

	/**
	 * should be use only if a child was created
	 * @param e
	 */
	private void addInChild(EnvironmentObject e) {
		int index=0;
		int n=0;
		
		for(int i=0;i<4;i++){  //4 is a parameter
			if(getChildren()[i].getBox().intersects(e.getBox())){
				n++;
				index=i;
			}
		}
		if(n==1){
			getChildren()[index].add(e);
			e.node=getChildren()[index];
		}else{
			objects.add(e);
			e.node=this;
		}
		//TODO need a way to determine where is an object when it's in 2 part of the tree
		/*for(EnvironmentObject o : objects){
			root.add(o);
		}*/
		
	}

	/**
	 * create a new box for the child number i following this scheme :
	 *  _______
	 * |_1_|_2_|
	 * |_3_|_4_|
	 * 
	 * @param box2
	 * @param i
	 * @return
	 */
	private Rectangle2f createChildBox(int i) {
		Rectangle2f returnBox = new Rectangle2f();
			switch(i){
			case 0:
				returnBox.setFromCorners(box.getCenterX(),box.getCenterY(),box.getMinX(),box.getMaxY());
				break;
			case 1:
				returnBox.setFromCorners(box.getCenterX(),box.getCenterY(),box.getMaxX(),box.getMaxY());
				break;
			case 2:
				returnBox.setFromCorners(box.getCenterX(),box.getCenterY(),box.getMinX(),box.getMinY());
				break;
			case 3:
				returnBox.setFromCorners(box.getCenterX(),box.getCenterY(),box.getMaxX(),box.getMinY());
				break;
			default:
				returnBox = box;
			}
			return returnBox;

	}

	public Rectangle2f getBox() {
		return box;
	}

	public void setBox(Rectangle2f box) {
		this.box = box;
	}

	public TreeNode[] getChildren() {
		return children;
	}

	public void setChildren(TreeNode[] children) {
		this.children = children;
	}

	/**
	 * deprecated : use DepthFirstIterator instead -> better performance
	 * @param t
	 */
	@Deprecated
	public void/*?*/ depthFirst(RTree t){

		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		/*assert(t.getRoot()!=null)*/ //TODO use assert keyword
		if(t.getRoot()!=null){
			stack.push(t.getRoot());
			while(!stack.isEmpty()){ //WARNING non stoppable
				TreeNode top = stack.pop();
				for(TreeNode child : top.getChildren()){
					stack.push(child);
				} // do something with top
			}
		}
	}

	public boolean hasChild() {
		if(children==null){
			return false;
		}else{
			return true;
		}
	}
	
	

}
