package midka.files;

import midka.visitor.Visitor;

public interface IFile {
    String accept(Visitor visitor);
}
