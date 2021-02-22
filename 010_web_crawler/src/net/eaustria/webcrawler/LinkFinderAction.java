/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.eaustria.webcrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 *
 * @author bmayr
 */
// Recursive Action for forkJoinFramework from Java7
public class LinkFinderAction extends RecursiveAction {

    private String url;
    private ILinkHandler cr;
    /**
     * Used for statistics
     */
    private static final long t0 = System.nanoTime();

    public LinkFinderAction(String url, ILinkHandler cr) {
        this.url = url;
        this.cr = cr;
        // ToDo: Implement Constructor
    }

    @Override
    public void compute() {
        if (cr.size() > 500) {
        } else if (!cr.visited(url)) {

            Parser parser;
            NodeFilter filter;
            NodeList list;
            List<LinkFinderAction> actions = new ArrayList<LinkFinderAction>();
            filter = new NodeClassFilter(LinkTag.class);
            try {
                parser = new Parser(url);
                list = parser.extractAllNodesThatMatch(filter);
                cr.addVisited(url);
                for (int i = 1; i < list.size(); i++) {

                    String link = list.elementAt(i).toHtml();
                    String[] linkSplit = link.split("\"");
                    if (linkSplit[1].contains("http")) {
                        actions.add(new LinkFinderAction(linkSplit[1], cr));
                        System.out.println(linkSplit[1]);
                    }

                    actions.add(new LinkFinderAction(url, cr));
                }
                invokeAll(actions);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        // ToDo:
        // 1. if crawler has not visited url yet:
        // 2. Create new list of recursiveActions
        // 3. Parse url
        // 4. extract all links from url
        // 5. add new Action for each sublink
        // 6. if size of crawler exceeds 500 -> print elapsed time for statistics
        // -> Do not forget to call ìnvokeAll on the actions!      
    }
}
