/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.parsing;

import java.util.Properties;

/**
 * @author Clinton Begin
 */
public class PropertyParser {

  private PropertyParser() {
    // Prevent Instantiation
  }

  public static String parse(String string, Properties variables) {
    VariableTokenHandler handler = new VariableTokenHandler(variables);
    // 创建GenericTokenParser对象，并指定其处理的占位符格式为"${}"
    GenericTokenParser parser = new GenericTokenParser("${", "}", handler);
    // 将默认值的处理委托给GenericTokenParser.parse()方法
    return parser.parse(string);
  }

  private static class VariableTokenHandler implements TokenHandler {
    // <properties>节点下定义的键值对，用于替换占位符
    private Properties variables;

    public VariableTokenHandler(Properties variables) {
      this.variables = variables;
    }

    @Override
    public String handleToken(String content) {
      // 直接查找variables集合
      if (variables != null && variables.containsKey(content)) {
        return variables.getProperty(content);
      }
      // variables集合为空
      return "${" + content + "}";
    }
  }
}
