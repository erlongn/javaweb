package cn.dalongn.web.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;

public class GetDateDirective extends Directive {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "getDate";
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return LINE;
	}

	@Override
	public boolean render(InternalContextAdapter context, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
		
		writer.write(new Date().toString());
		return true; 
	}

}
