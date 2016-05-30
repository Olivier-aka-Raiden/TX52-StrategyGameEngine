package tx52.environment;

import java.util.Collection;

import org.arakhne.afc.math.continous.object2d.Rectangle2f;

public class RTree {
	
	private TreeNode root;
	
	/**
	 * creation of a RTree with his range represented by a box
	 * @param objects
	 * @param box
	 */
	RTree(Collection<EnvironmentObject> objects,Rectangle2f box){
		setRoot(new TreeNode(new Rectangle2f(box)));
		if(objects!=null){
			for( EnvironmentObject o : objects){
				getRoot().add(o);
			}
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
