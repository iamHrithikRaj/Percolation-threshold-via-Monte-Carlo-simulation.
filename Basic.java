import java.util.Scanner;
class WeigthedQuickUnionFind{
	int[] id;
	int[] size;
	WeigthedQuickUnionFind(int n){
		id = new int[n];
		for(int i=0;i<n;++i){
			id[i] = i;
			size[i] = 1;
		}
	}
	public int root(int node){/* finds the parent node */
		while(node != id[node])
			node = id[id[node]];
		return node;
	}
	public boolean check(int node_1,int node_2){/* is connected? */
		return root(node_1) == root(node_2);
	}
	public void union(int node_1,int node_2){
		int Rnode_1 = root(node_1);
		int Rnode_2 = root(node_2);
		if(size[Rnode_1] >= size[Rnode_2]){
			id[Rnode_2] = Rnode_1; /*more efficient to move 
			the smaller cluster*/
			size[Rnode_1] +=size[Rnode_2];
		}else{
			id[Rnode_1] = Rnode_2;
			size[Rnode_2] += size[Rnode_1];
		}
	}
}