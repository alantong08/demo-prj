package com.tiger.worker.cases;


public class MinNodeDepthSolution {

    private TreeNodeObj treeNode;

    public int run(TreeNodeObj treeNode){
        if(treeNode==null)
            return 0;
        if(treeNode.left==null || treeNode.right==null)
            return 1;

        return 0;//todo:not finished
    }

    class TreeNodeObj{
        int value;
        TreeNodeObj left;
        TreeNodeObj right;

        public TreeNodeObj(int x){
            this.value = x;
        }
    }
}
