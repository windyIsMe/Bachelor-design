<template>
    <div>
        <div class="edit_container">
        	<quill-editor v-model="content"
                ref="myQuillEditor"
                class="editer"
                :options="editorOption"
                @ready="onEditorReady($event)">
  			</quill-editor>
        </div>
        <div class="submit_btn">
  			<el-button type="primary" @click="submit">发布</el-button>
        </div>
    </div>
</template>

<script>
    import { quillEditor } from 'vue-quill-editor'

    export default {
        data(){
            return {
                content: '<h3>此处编写公告</h3>',
			    editorOption: {
			        
		        }
            }
        },
    	components: {
    		quillEditor,
    	},
        computed: {
          	editor() {
	        	return this.$refs.myQuillEditor.quill
	      	}
        },
        methods: {
		    onEditorReady(editor) {
		        console.log('editor ready!', editor)
		    },
		    submit(){
                console.log(this.content);
                let text = this.content.replace(/<[^>]*>|/g,"");
                console.log(text);
                console.log(this.$store.state.name);
                this.axios.post('http://127.0.0.1:8081/addAnnounce', {
                    content: text,
                    name: this.$store.state.name
                }).then((response) => {
                    console.log(response.data);
                    if (response.data > 0) {
                        this.$message.success('发布成功！');
                    } else {
                        this.$message.error('发布失败！');
                    }
                }).catch((error) => {
                    console.log(error);
                })
                
            }
        },
    }
</script>

<style>
	.edit_container{
		padding: 40px;
		margin-bottom: 40px;
	}
	.editer{
		height: 350px;
	}
	.submit_btn{
		text-align: center;
	}
</style>
