JAVAC=javac -Xlint:none
FULL_CLASSPATH=.
CLASSES= jode/bytecode/Reference.class \
        jode/bytecode/GrowableConstantPool.class \
        jode/bytecode/Handler.class \
        jode/bytecode/FieldInfo.class \
        jode/bytecode/BytecodeInfo.class \
        jode/bytecode/ConstantPool.class \
        jode/bytecode/TypeSignature.class \
        jode/bytecode/InnerClassInfo.class \
        jode/bytecode/BinaryInfo.class \
        jode/bytecode/Opcodes.class \
        jode/bytecode/MethodInfo.class \
        jode/bytecode/ClassInfo.class \
        jode/bytecode/SearchPath.class \
        jode/bytecode/ClassFormatException.class \
        jode/bytecode/Instruction.class \
        jode/bytecode/LocalVariableInfo.class \
        jode/bytecode/LineNumber.class \
        jode/GlobalOptions.class \
        jode/flow/JsrBlock.class \
        jode/flow/SequentialBlock.class \
        jode/flow/CombineIfGotoExpressions.class \
        jode/flow/FinallyBlock.class \
        jode/flow/CreatePrePostIncExpression.class \
        jode/flow/SpecialBlock.class \
        jode/flow/BreakBlock.class \
        jode/flow/LoopBlock.class \
        jode/flow/CreateAssignExpression.class \
        jode/flow/CreateCheckNull.class \
        jode/flow/VariableStack.class \
        jode/flow/TransformExceptionHandlers.class \
        jode/flow/VariableSet.class \
        jode/flow/CreateNewConstructor.class \
        jode/flow/ContinueBlock.class \
        jode/flow/ConditionalBlock.class \
        jode/flow/ReturnBlock.class \
        jode/flow/RetBlock.class \
        jode/flow/IfThenElseBlock.class \
        jode/flow/CreateConstantArray.class \
        jode/flow/SynchronizedBlock.class \
        jode/flow/InstructionBlock.class \
        jode/flow/CreateIfThenElseOperator.class \
        jode/flow/CaseBlock.class \
        jode/flow/ThrowBlock.class \
        jode/flow/TryBlock.class \
        jode/flow/Jump.class \
        jode/flow/InstructionContainer.class \
        jode/flow/CompleteSynchronized.class \
        jode/flow/DescriptionBlock.class \
        jode/flow/SwitchBlock.class \
        jode/flow/EmptyBlock.class \
        jode/flow/CreateForInitializer.class \
        jode/flow/CatchBlock.class \
        jode/flow/CreateExpression.class \
        jode/flow/BreakableBlock.class \
        jode/flow/FlowBlock.class \
        jode/flow/CreateClassField.class \
        jode/flow/StructuredBlock.class \
        jode/flow/TransformConstructors.class \
        jode/flow/SlotSet.class \
        jode/jvm/SimpleRuntimeEnvironment.class \
        jode/jvm/InterpreterException.class \
        jode/jvm/VerifyException.class \
        jode/jvm/CodeVerifier.class \
        jode/jvm/NewObject.class \
        jode/jvm/RuntimeEnvironment.class \
        jode/jvm/Value.class \
        jode/jvm/SyntheticAnalyzer.class \
        jode/jvm/Interpreter.class \
        jode/obfuscator/CodeAnalyzer.class \
        jode/obfuscator/MethodIdentifier.class \
        jode/obfuscator/Main.class \
        jode/obfuscator/TranslationTable.class \
        jode/obfuscator/ScriptParser.class \
        jode/obfuscator/ParseException.class \
        jode/obfuscator/LocalIdentifier.class \
        jode/obfuscator/IdentifierMatcher.class \
        jode/obfuscator/Renamer.class \
        jode/obfuscator/ClassIdentifier.class \
        jode/obfuscator/PackageIdentifier.class \
        jode/obfuscator/ClassBundle.class \
        jode/obfuscator/modules/ModifierMatcher.class \
        jode/obfuscator/modules/NameSwapper.class \
        jode/obfuscator/modules/ConstantAnalyzer.class \
        jode/obfuscator/modules/WildCard.class \
        jode/obfuscator/modules/KeywordRenamer.class \
        jode/obfuscator/modules/StrongRenamer.class \
        jode/obfuscator/modules/ListRenamer.class \
        jode/obfuscator/modules/SimpleAnalyzer.class \
        jode/obfuscator/modules/MultiIdentifierMatcher.class \
        jode/obfuscator/modules/SerializePreserver.class \
        jode/obfuscator/modules/LocalOptimizer.class \
        jode/obfuscator/modules/UniqueRenamer.class \
        jode/obfuscator/modules/RemovePopAnalyzer.class \
        jode/obfuscator/CodeTransformer.class \
        jode/obfuscator/OptionHandler.class \
        jode/obfuscator/ConstantRuntimeEnvironment.class \
        jode/obfuscator/Identifier.class \
        jode/obfuscator/FieldIdentifier.class \
        jode/decompiler/OuterValues.class \
        jode/decompiler/Analyzer.class \
        jode/decompiler/ClassDeclarer.class \
        jode/decompiler/Window.class \
        jode/decompiler/Main.class \
        jode/decompiler/TabbedPrintWriter.class \
        jode/decompiler/ClassAnalyzer.class \
        jode/decompiler/Options.class \
        jode/decompiler/LocalVariableRangeList.class \
        jode/decompiler/LocalVariableTable.class \
        jode/decompiler/MethodAnalyzer.class \
        jode/decompiler/ProgressListener.class \
        jode/decompiler/Declarable.class \
        jode/decompiler/OuterValueListener.class \
        jode/decompiler/Decompiler.class \
        jode/decompiler/Scope.class \
        jode/decompiler/LocalInfo.class \
        jode/decompiler/Opcodes.class \
        jode/decompiler/DeadCodeAnalysis.class \
        jode/decompiler/LocalVarEntry.class \
        jode/decompiler/Applet.class \
        jode/decompiler/ImportHandler.class \
        jode/decompiler/FieldAnalyzer.class \
        jode/AssertError.class \
        jode/util/SimpleSet.class \
        jode/util/SimpleMap.class \
        jode/util/ArrayEnum.class \
        jode/util/UnifyHash.class \
        jode/type/MethodType.class \
        jode/type/NullType.class \
        jode/type/ArrayType.class \
        jode/type/ReferenceType.class \
        jode/type/RangeType.class \
        jode/type/IntegerType.class \
        jode/type/ClassInterfacesType.class \
        jode/type/Type.class \
        jode/expr/NewOperator.class \
        jode/expr/NopOperator.class \
        jode/expr/CheckCastOperator.class \
        jode/expr/CompareToIntOperator.class \
        jode/expr/ClassFieldOperator.class \
        jode/expr/Expression.class \
        jode/expr/InstanceOfOperator.class \
        jode/expr/NewArrayOperator.class \
        jode/expr/ConstantArrayOperator.class \
        jode/expr/GetFieldOperator.class \
        jode/expr/ArrayLoadOperator.class \
        jode/expr/InvokeOperator.class \
        jode/expr/LocalLoadOperator.class \
        jode/expr/SimpleOperator.class \
        jode/expr/PrePostFixOperator.class \
        jode/expr/MonitorEnterOperator.class \
        jode/expr/CheckNullOperator.class \
        jode/expr/UnaryOperator.class \
        jode/expr/LocalVarOperator.class \
        jode/expr/PopOperator.class \
        jode/expr/ConstOperator.class \
        jode/expr/MonitorExitOperator.class \
        jode/expr/PutFieldOperator.class \
        jode/expr/ConvertOperator.class \
        jode/expr/Operator.class \
        jode/expr/FieldOperator.class \
        jode/expr/OuterLocalOperator.class \
        jode/expr/LocalStoreOperator.class \
        jode/expr/ThisOperator.class \
        jode/expr/BinaryOperator.class \
        jode/expr/StringAddOperator.class \
        jode/expr/LValueExpression.class \
        jode/expr/ArrayStoreOperator.class \
        jode/expr/CompareBinaryOperator.class \
        jode/expr/ArrayLengthOperator.class \
        jode/expr/IfThenElseOperator.class \
        jode/expr/ShiftOperator.class \
        jode/expr/CombineableOperator.class \
        jode/expr/NoArgOperator.class \
        jode/expr/MatchableOperator.class \
        jode/expr/IIncOperator.class \
        jode/expr/StoreInstruction.class \
        jode/expr/CompareUnaryOperator.class \
        test/HintTypeTest.class \
        test/MethodScopedClass.class \
        test/Expressions.class \
        test/AnonymousClass.class \
        test/InnerClass.class \
        test/InlinedAnon.class \
        test/IfCombine.class \
        test/InnerCompat.class \
        test/NestedAnon.class \
        test/Unreach.class \
        test/LocalTypes.class \
        test/JavacBug.class \
        test/ConstantTypes.class \
        test/PrivateHideTest.class \
        test/TryCatch.class \
        test/For.class \
        test/ClassOpTest.class \
        test/Flow.class \
        test/TriadicExpr.class \
        test/ArrayCloneTest.class \
        test/ArrayTest.class \
        test/OptimizerTest.class \
        test/AnonymousJavac.class \
        test/AssignOp.class \
        gnu/getopt/GetoptDemo.class \
        gnu/getopt/Getopt.class \
        gnu/getopt/LongOpt.class \
        jode/Jozin.class

#        jode/swingui/Main.class \
#        jode/swingui/HierarchyTreeModel.class \
#        jode/swingui/PackagesTreeModel.class \
#        jode/obfuscator/modules/LocalizeFieldTransformer.class \

#        test/InlineTest.class \
#        test/OptimizeTest.class \
#        test/ResolveConflicts.class \
#        test/CountOpcodes.class \


all: $(CLASSES)

#	$(JAVAC) -classpath $(FULL_CLASSPATH) $(JAVAS)

clean:
	rm -f $(CLASSES)

%.class: %.java
	$(JAVAC) -classpath $(FULL_CLASSPATH)  $<
